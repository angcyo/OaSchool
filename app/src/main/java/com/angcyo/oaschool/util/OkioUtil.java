package com.angcyo.oaschool.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okio.BufferedSink;
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
}
