/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author zyp
 */
public class API {

    public static String urlDecode(String url) {
        return URLDecoder.decode(url);
    }

    public static String urlDecode(String url, String encoding) {
        try {
            return URLDecoder.decode(url, encoding);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * 获取@玩家CQ的代码
     * @param user_id
     * @return 
     */
    public static String getAtCode(long user_id) {
        return "[CQ:at,qq=" + user_id + "]";
    }
}
