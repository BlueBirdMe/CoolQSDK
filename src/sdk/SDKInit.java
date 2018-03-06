/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import sdk.event.CoolQEventManager;
import sdk.http.HTTPServer;
import sdk.util.API;
import sdk.util.FileUtils;
import sdk.util.bean.BeanUtils;

/**
 *
 * @author zyp
 */
public class SDKInit {

    /**
     * 记录所有类
     */
    public static final Set<Class> ALLCLASS = new HashSet();
    
    public static void init(Map<Class,String> init) {
        try {
            System.out.println("[CoolQSDK]包扫描...");
            initClass(SDKInit.class, "sdk.");
            if(init != null){
                init.entrySet().forEach(en -> initClass(en.getKey(), en.getValue()));
            }
            System.out.println("[CoolQSDK]注册Bean...");
            registerBean();
            System.out.println("[CoolQSDK]启动事件管理器...");
            CoolQEventManager.getInstance().init();
            System.out.println("[CoolQSDK]启动HTTPServer...");
            HTTPServer.start();
            System.out.println("[CoolQSDK]加载完成...");
        } catch (IOException ex) {
            System.out.println("[CoolQSDK]启动失败...");
            throw new RuntimeException(ex);
        }
    }

    private static void registerBean() {
        ALLCLASS.forEach(p -> BeanUtils.registerClass(p));
    }

    /**
     * 扫描clazz所在jar/文件夹下的所有以pkg开头的类
     * @param clazz
     * @param pkg
     */
    public static void initClass(Class clazz, String pkg){
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = API.urlDecode(path, "UTF-8");
        if(path.startsWith("/")){
            path = path.substring(1);
        }
        System.out.println("[CoolQSDK]scan classes in ("+path+") whose name start with "+pkg);
        int n = ALLCLASS.size();
        File f = new File(path);
        if (f.isFile()) {
            JarFile jar;
            try {
                jar = new JarFile(f);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (name.endsWith(".class")) {
                    if (name.startsWith("/")) {
                        name = name.substring(1, name.length() - 6);
                    } else {
                        name = name.substring(0, name.length() - 6);
                    }
                    name = name.replace("/", ".");
                    try {
                        if (name.startsWith(pkg)) {
                            ALLCLASS.add(Class.forName(name));
//                            System.out.println("[CoolQSDK] register class:"+name);
                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println("[CoolQSDK]Class load fail:" + name);
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else {
            int i = f.getPath().length() + 1;
            for (String s : FileUtils.FileList(f)) {
                if (s.endsWith(".class")) {
                    String name = s.substring(i, s.length() - 6).replace(File.separator, ".");
                    try {
                        if (name.startsWith(pkg)) {
                            ALLCLASS.add(Class.forName(name));
//                            System.out.println("[CoolQSDK] register class:"+name);
                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println("[CoolQSDK]Class load fail:" + name);
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        n = ALLCLASS.size() - n;
        System.out.println("[CoolQSDK]find "+n+" new Class");
    }
    
}
