package peng.zhi.liu.utils;

import com.aliyuncs.ram.model.v20150501.RemoveUserFromGroupRequest;

public class BaseContextIndenty {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void setThreadLocal(String indenty){
        threadLocal.set(indenty);
    }
    public static String getThreadLocal(){
        return threadLocal.get();
    }
    public static void removeThreadLocal(){
        threadLocal.remove();
    }
}
