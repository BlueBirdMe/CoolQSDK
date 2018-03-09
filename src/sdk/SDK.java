/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zyp
 */
public class SDK {

    static SimpleDateFormat format = new SimpleDateFormat("[yyyy//MM/dd_HH:mm:ss]");
    public static BufferedWriter bw;

    public static void init() {
        SDKInit.init(null);
    }

    public static void log(String s) {
        if (bw != null) {
            try {
                bw.write(format.format(new Date()) + " " + s);
                bw.newLine();
                bw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
