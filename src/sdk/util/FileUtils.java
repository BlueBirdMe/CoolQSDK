/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author zyp
 */
public class FileUtils {

    public static boolean createFile(File f) throws IOException {
        return f.createNewFile();
    }

    public static boolean createFile(String path) throws IOException {
        return createFile(new File(path));
    }

    /**
     * 字符串写入文件
     *
     * @param f
     * @param append 是否追加到文件末尾
     * @param content 字符串
     * @param encoding 编码
     */
    public static void writeContent(File f, boolean append, String content, Charset encoding) {
        writeData(f, append, content.getBytes(encoding));
    }

    /**
     * 数据写入文件,文件必须存在
     *
     * @param f
     * @param append 是否追加到文件末尾
     * @param data
     */
    public static void writeData(File f, boolean append, byte[] data) {
        f = getFile(f.getPath(), true);//创建文件
        try (OutputStream os = new FileOutputStream(f, append)) {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            IOUtils.transform(in, os);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] readData(File f) {
        try {
            InputStream is = new FileInputStream(f);
            return IOUtils.readData(is);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 以指定编码读取文件内的字符串
     *
     * @param f
     * @param encoding
     *
     * @return
     */
    public static String readContent(File f, Charset encoding) {
        try (InputStream is = new FileInputStream(f);) {
            return IOUtils.readContent(is, encoding);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 读取文件所有文本行
     *
     * @param f
     * @param encoding
     *
     * @return
     */
    public static ArrayList<String> readLines(File f, Charset encoding) {
        ArrayList<String> lines = new ArrayList();
        String s;
        try (BufferedReader br = getReader(f, encoding)) {
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return lines;
    }

    /**
     * 将文本行写入文件
     *
     * @param f
     * @param encoding
     * @param append 是否追加
     * @param lines
     */
    public static void writeLines(File f, Charset encoding, boolean append, Collection<String> lines) {
        Iterator<String> it = lines.iterator();
        f = getFile(f.getPath(), true);
        try (BufferedWriter bw = getWriter(f, encoding, append)) {
            while (it.hasNext()) {
                bw.write(it.next());
                bw.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取目录,默认不创建
     *
     * @param path
     *
     * @return
     */
    public static File getFolder(String path) {
        return getFolder(path, false);
    }

    public static File getFolder(String path, boolean create) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    /**
     * 获取文件,默认不创建
     *
     * @param path
     *
     * @return
     */
    public static File getFile(String path) {
        return getFile(path, false);
    }

    public static File getFile(String path, boolean create) {
        File f = new File(path);
        if (!f.exists()) {
            getFolder(f.getParent(), true);
            try {
                f.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return f;
    }

    public static List<String> FileList(File file, String subfix) {
        List<String> filelist = new ArrayList<String>();
        if (file.isFile()) {
            if (file.getPath().endsWith(subfix)) {
                filelist.add(file.getPath());
            }
            return filelist;
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    if (f.getPath().endsWith(subfix)) {
                        filelist.add(f.getPath());
                    }
                } else {
                    filelist.addAll(FileList(f));
                }
            }
        }
        return filelist;
    }

    /**
     * 返回文件夹下所有文件的路径,不含空文件夹
     *
     * @param file
     *
     * @return
     */
    public static List<String> FileList(File file) {
        List<String> filelist = new ArrayList<String>();
        if (file.isFile()) {
            filelist.add(file.getPath());
            return filelist;
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    filelist.add(f.getPath());
                } else {
                    filelist.addAll(FileList(f));
                }
            }
        }
        return filelist;
    }

    /**
     * 打开BufferedWriter,默认UTF-8编码,非追加
     *
     * @param f
     *
     * @return
     *
     * @throws FileNotFoundException
     */
    public static BufferedWriter getWriter(File f) {
        return getWriter(f, Charset.forName("UTF-8"), false);
    }

    /**
     * 打开BufferedWriter,默认UTF-8编码
     *
     * @param f
     * @param append 是否追加
     *
     * @return
     *
     * @throws FileNotFoundException
     */
    public static BufferedWriter getWriter(File f, boolean append) {
        return getWriter(f, Charset.forName("UTF-8"), append);
    }

    /**
     * 打开BufferedWriter,追加模式
     *
     * @param f
     * @param encoding
     *
     * @return
     *
     * @throws FileNotFoundException
     */
    public static BufferedWriter getWriter(File f, Charset encoding) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false), encoding));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 打开BufferedWriter
     *
     * @param f
     * @param encoding 编码
     * @param append 追加
     *
     * @return
     *
     * @throws FileNotFoundException
     */
    public static BufferedWriter getWriter(File f, Charset encoding, boolean append) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, append), encoding));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static BufferedReader getReader(File f) throws FileNotFoundException {
        return getReader(f, Charset.forName("UTF-8"));
    }

    public static BufferedReader getReader(File f, Charset encoding) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(f), encoding));
    }

    public static void copy(File from, File to) throws IOException {
        copy(new FileInputStream(from), to);
    }

    public static void copy(InputStream is, File to) throws IOException {
        createFile(to);
        copy(is, new FileOutputStream(to));
    }

    /**
     * 将输入流的数据拷贝到输出流
     *
     * @param is
     * @param os
     *
     * @throws IOException
     */
    public static void copy(final InputStream is, final OutputStream os) throws IOException {
        try (InputStream in = is; OutputStream out = os) {
            int i = 0;
            byte[] bs = new byte[1024];
            while ((i = in.read(bs)) != -1) {
                out.write(bs, 0, i);
            }
        }
    }

    //删除文件或递归删除目录和其下所有目录和文件
    public static void delDirectory(File file) {
        if (file.isDirectory()) {
            for (File delFile : file.listFiles()) {
                delDirectory(delFile);
            }
        }
        file.delete();
    }
}
