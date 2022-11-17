import java.io.*;
import java.nio.charset.StandardCharsets;

public class getLog {
    public static String getLog(String fileName) {
        String str="";
        File file=new File(fileName);
        try {
            FileInputStream in=new FileInputStream(file);
            // size 为字串的长度 ，这里一次性读完
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            str=new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
