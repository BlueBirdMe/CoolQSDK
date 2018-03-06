/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.http;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import sdk.SDKConfig;

/**
 * HTTP服务器,监听CoolQ上报消息
 *
 * @author zyp
 */
public class HTTPServer {

    public static void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(SDKConfig.SDKHost, SDKConfig.SDKPort), 0);
        server.createContext("/", new MessageHandler());
        server.start();
    }
}
