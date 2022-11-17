import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.*;
public class matchIP {
    static long counter = 1;
    public static String printRecord(String log){
        String pattern = "^(\\S+) (\\S+) (\\S+) " +
                "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)" +
                " (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)";

        Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher m = r.matcher(log);
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);

        LinkedHashMap<String, String> countIP = new LinkedHashMap<>();

            while (m.find()) {
                String IP = m.group(1);
                String date = m.group(4);
                String responseString = m.group(8);
                if(responseString.equals("200"))
            {
                countIP.put(IP, date);
            }
        }
        for (Map.Entry entry : countIP.entrySet()) {
            String userIP = getIpGeolocation.getIPGeolocation(entry.getKey().toString());
            stringBuilder.append("序号：" + counter + "  " + "IP地址：" + entry.getKey() + "  最后访问时间：" + entry.getValue() + "  " + userIP + "\n");
            counter++;
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        final String log = getLog.getLog("/var/log/apache2/access.log");
        final String exlog = matchIP.printRecord(log);
        exportLog.exportLog(exlog);
    }
}