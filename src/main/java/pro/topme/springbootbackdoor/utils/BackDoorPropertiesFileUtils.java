package pro.topme.springbootbackdoor.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author XuJiakai
 *  2019/11/15 17:49
 */
public class BackDoorPropertiesFileUtils {
    private String fileName;

    public BackDoorPropertiesFileUtils(String fileName) {
        this.fileName = fileName;
    }

    private void writeProperty(String key, String value) {
        OutputStream os = null;
        Properties p = new Properties();
        try {
            os = new FileOutputStream(fileName);
            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }

    }

    private Properties getPropertiesFile() {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            File file = new File(fileName);
            in = new FileInputStream(file);
            prop.load(in);
            if (!prop.containsKey(key)) {
                prop.setProperty(key, System.currentTimeMillis() + "");
            }
            return prop;
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    public Long getTime() {
        try {
            Long property = Long.parseLong(getPropertiesFile().getProperty(key));
            return property;
        } catch (Exception e) {
        }
        long l = System.currentTimeMillis();
        writeProperty(key, l + "");
        return l;
    }

    public boolean setTime(Long time) {
        writeProperty(key, time + "");
        return true;
    }

    private static String key = "t";
}
