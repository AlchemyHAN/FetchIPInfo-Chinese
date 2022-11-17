import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.sf.json.*;

public class getIpGeolocation {
    public static String getIPGeolocation(String ip) {

        HttpURLConnection con;
        BufferedReader buffer;
        StringBuffer resultBuffer;

        try {
            //不好的api
            //URL url = new URL("https://ip.useragentinfo.com/json?ip=" + ip);
            URL url = new URL("https://www.fkcoder.com/ip?ip=" + ip);
            // 得到连接对象
            con = (HttpURLConnection) url.openConnection();
            // 设置请求类型
            con.setRequestMethod("GET");
            // 设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Host", "www.fkcoder.com");
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty(
                    "user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
            con.setRequestProperty("Content-Type",
                    "application/json;charset=utf-8");
            // 允许写出
            con.setDoOutput(true);
            // 允许读入
            con.setDoInput(true);
            // 不使用缓存
            con.setUseCaches(false);
            // 得到响应码
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 得到响应流
                InputStream inputStream = con.getInputStream();
                // 将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                if (resultBuffer.toString().indexOf("{") >= 0) {
                    JSONObject addArr = JSONObject.fromObject(resultBuffer.toString().substring(resultBuffer.toString().indexOf("{")));
                    String country = addArr.getString("country") + " ";
                    String region = addArr.getString("region") + " ";
                    String province = addArr.getString("province") + " ";
                    String city = addArr.getString("city") + " ";
                    String isp = addArr.getString("isp") + " ";

                    String[] abc = {country, region, province, city, isp};
                    for(int i = 0; i < abc.length; i++)
                    {
                        if(abc[i].equals("0 "))
                        {
                            abc[i] = "";
                        }
                    }

                    String result = abc[0] + abc[1] + abc[2] + abc[3] + abc[4];
                    return result;

                } else {
                    return "NOT";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "错误！";
    }
}
