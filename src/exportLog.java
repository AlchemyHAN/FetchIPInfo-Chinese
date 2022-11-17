import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.*;

public class exportLog {
    private static long num = 0;
    public static void exportLog(String exlog){
        File ex = new File("/var/log/apache2/access.log.Chinese");
        try {
            if (!ex.exists())
                ex.createNewFile();
            FileWriter fileWriter = new FileWriter(ex.getName());
            fileWriter.write(exlog);
            RandomAccessFile randomAccessFile = new RandomAccessFile("/var/log/apache2/access.log","rw");
            num = randomAccessFile.length();
            fileWriter.close();

            IOFileFilter files = FileFilterUtils.nameFileFilter("access.log");
            FileAlterationObserver observer = new FileAlterationObserver("/var/log/apache2/", files);
            FileListener fileListener = new FileListener();
            observer.addListener(fileListener);
            FileAlterationMonitor fileMonitor = new FileAlterationMonitor(1000,observer);
            fileMonitor.start();
        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLog() throws IOException {
        File file = new File("/var/log/apache2/access.log");
        RandomAccessFile randomFileE = new RandomAccessFile(file,"rw");
        randomFileE.seek(num);
        String s = randomFileE.readLine();
        RandomAccessFile randomFileC = new RandomAccessFile("/var/log/apache2/access.log.Chinese", "rw");
        for(;s!= null;s = randomFileE.readLine()){
            String appendRecord = matchIP.printRecord(s);
            long fileLength = randomFileC.length();
            //将写文件指针移到文件尾。
            randomFileC.seek(fileLength);
            randomFileC.write(appendRecord.getBytes());
        }
        randomFileC.close();
        num= randomFileE.length();
    }

}
