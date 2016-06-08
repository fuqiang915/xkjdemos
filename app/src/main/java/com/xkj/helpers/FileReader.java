package com.xkj.helpers;

import android.content.Context;
import org.apache.http.util.EncodingUtils;

import java.io.InputStream;

/**
 * Created by fuqiang on 15/1/29.
 */
public class FileReader {
    /**
     * 读取assets文件数据
     * @param context
     * @param fileName  文件相对于assets目录的路径
     * @return
     */
    public String readFileFromAssets(Context context, String fileName) {
        String res = "";
        try {

            //得到资源中的asset数据流
            InputStream in = context.getResources().getAssets().open(fileName);

            int length = in.available();
            byte[] buffer = new byte[length];

            in.read(buffer);
            in.close();
            res = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
