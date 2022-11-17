import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.*;

public class FileListener extends FileAlterationListenerAdaptor {
    @Override
    public void onFileChange(File file) {
        try {
                exportLog.updateLog();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }
