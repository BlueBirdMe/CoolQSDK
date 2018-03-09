/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Properties;
import sdk.util.FileUtils;

/**
 *
 * @author zyp
 */
public class SDKConfig {

    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset GBK = Charset.forName("GBK");
    /**
     * 酷QAPI地址
     */
    public static String coolQURL;
    /**
     * CoolQ上报ip
     */
    public static String SDKHost;
    /**
     * CoolQ上报端口
     */
    public static int SDKPort;
    /**
     * 是否记录所有消息
     */
    public static boolean debug;
    
    static {
        try {
            File f = new File("CoolQSDK.properties");
            if (!f.exists()) {
                FileUtils.copy(SDK.class.getResourceAsStream("/CoolQSDK.properties"), f);//输出配置文件
            }
            Properties config = new Properties();
            config.load(FileUtils.getReader(f, GBK));
            coolQURL = config.getProperty("coolQURL");
            SDKHost = config.getProperty("SDKHost");
            SDKPort = Integer.valueOf(config.getProperty("SDKPort"));
            debug = Boolean.valueOf(config.getProperty("debug"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CoolQSDK]配置加载失败...");
        }
    }
}
