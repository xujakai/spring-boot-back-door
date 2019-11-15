package pro.topme.springbootbackdoor.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author XuJiakai
 * @ClassName: NetWorkUtils
 * @Description:
 * @date 2019/11/15 17:48
 */
public class NetWorkUtils {

    private String remoteAddress;
    private String timeAddress;

    public NetWorkUtils(String remoteAddress, String timeAddress) {
        this.remoteAddress = remoteAddress;
        this.timeAddress = timeAddress;
    }

    /**
     * 传入需要连接的IP，返回是否连接成功
     *
     * @return
     */
    public boolean isReachable() {
        boolean reachable = false;
        try {
            InetAddress address = InetAddress.getByName(remoteAddress);
            reachable = address.isReachable(5000);
        } catch (Exception e) {
        }
        return reachable;
    }

    public Long getWebsiteDatetime() {
        try {
            URL url = new URL(timeAddress);
            URLConnection uc = url.openConnection();
            uc.connect();
            return uc.getDate();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public Long getCurrentTime(boolean reachable) throws Exception {
        if (reachable) {
            return getWebsiteDatetime();
        } else {
            long l = System.currentTimeMillis();
            Long time = BackDoorPropertiesFileUtils.getTime();
            if (time > l) {
                throw new Exception("Local time read exception");
            }
            return l;
        }
    }
    public Long getCurrentTime() throws Exception {
        return getCurrentTime(isReachable());
    }
}
