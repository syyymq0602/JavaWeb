package example.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DownLoadUtils {
    public static String getFileName(String agent,String filename) throws UnsupportedEncodingException {
        if(agent.contains("MISE")){
            // IE浏览器
            filename = URLEncoder.encode(filename,"utf-8");
            filename = filename.replace("+"," ");
        }else{
            // 其他浏览器
            filename = URLEncoder.encode(filename,"utf-8");
        }
        return filename;
    }
}
