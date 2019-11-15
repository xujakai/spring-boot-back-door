package pro.topme.springbootbackdoor.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author XuJiakai
 * @ClassName: BackDoorPropertiesFileUtils
 * @Description:
 * @date 2019/11/15 17:49
 */
public class BackDoorPropertiesFileUtils {

    private static void writeProperty(String key, String value) {
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

    private static Properties getPropertiesFile() {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            in = Object.class.getResourceAsStream(fileName);
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

    public static Long getTime() {
        try {
            Long property = Long.parseLong(getPropertiesFile().getProperty(key));
            return property;
        } catch (Exception e) {
        }
        long l = System.currentTimeMillis();
        writeProperty(key, l + "");
        return l;
    }

    public static boolean setTime(Long time) {
        writeProperty(key, time + "");
        return true;
    }

    private static String key = "t";
    private static String fileName = System.getProperty("java.io.tmpdir") + "open.api.ini";
}
