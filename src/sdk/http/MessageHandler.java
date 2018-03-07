/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONObject;
import sdk.SDKConfig;
import sdk.coolq.api.IReport;
import sdk.util.IOUtils;
import sdk.util.bean.BeanUtils;

/**
 * 消息处理器,将酷Q上报的消息进行解析并广播
 *
 * @author zyp
 */
public class MessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String message = IOUtils.readContent(exchange.getRequestBody(), SDKConfig.UTF_8);
//        System.out.println("[CoolQSDK]收到上报消息: " + message);
        JSONObject json;
        try {
            json = new JSONObject(message);
        } catch (Exception e) {
            System.out.println("can not build json from message:" + message);
            e.printStackTrace();
            exchange.close();
            return;
        }
        IReport ir;
        try {
            ir = BeanUtils.create(IReport.class, json);
        } catch (Exception e) {
            System.out.println("can not build bean from message:" + message);
            e.printStackTrace();
            exchange.close();
            return;
        }
        try {
//            System.out.println(System.currentTimeMillis() + "  开始广播");
            ir.fire();
//            System.out.println(System.currentTimeMillis() + "   广播结束");
        } catch (Throwable ex) {//不应该抛出异常
            ex.printStackTrace();
            System.out.println("error when fire report:" + message);
            exchange.close();
            return;
        }
//        System.out.println(System.currentTimeMillis() + "   发送状态码");
        exchange.sendResponseHeaders(200, 0);
//        System.out.println(System.currentTimeMillis() + "   发送完成");
        try (OutputStream os = exchange.getResponseBody()) {
            if (ir.getReply() != null) {
                String replyMessage = BeanUtils.toString(ir.getReply());
                os.write(replyMessage.getBytes(SDKConfig.UTF_8));
            }
        }
//        System.out.println(System.currentTimeMillis() + "   关闭请求");
    }
}
