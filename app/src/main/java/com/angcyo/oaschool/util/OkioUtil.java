package com.angcyo.oaschool.util;

import com.angcyo.oaschool.components.RConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class OkioUtil {


    public static void writeToFile(String data) {
        writeToFile(Util.getDateAndTime() + "-->" + data + "\n", new File("/mnt/sdcard/log.txt"), true);
    }

    public static void writeToFile(String data, File file, boolean isAppend) {
        try {
            BufferedSink bufferedSink = Okio.buffer(isAppend ? Okio.appendingSink(file) : Okio.sink(file));
            bufferedSink.write(ByteString.encodeUtf8(data));
            bufferedSink.flush();
            bufferedSink.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readIp() {
        String ip = readFromFile(RConstant.SER_IP_FILE_PATH);
        if (Util.isEmpty(ip)) {
            ip = RConstant.SER_IP;
        }
        return ip;
    }

    public static String readFromFile(String filePath) {
        String source = "";
        try {
            BufferedSource bufferedSource = Okio.buffer(Okio.source(new File(filePath)));
            source = bufferedSource.readUtf8();
            bufferedSource.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static String writeToFileFromUrl(String url, String filePath, String fileName) {
        try {
            File folder = new File(filePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(filePath + File.separator + fileName);
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream in = httpConnection.getInputStream();
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));//用于写
            bufferedSink.writeAll(Okio.buffer(Okio.source(in)));//用于读
            in.close();
            bufferedSink.flush();
            bufferedSink.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath + File.separator + fileName;
    }
}
