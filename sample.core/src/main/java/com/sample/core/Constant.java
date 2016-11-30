package com.sample.core;

/**
 * Created by andongxu on 9/18/16.
 */
public class Constant {

    /**
     * 成功
     */
    public static String [] SUCCESS = {"000000", "success"};
    /**
     * 未知错误
     */
    public static String [] EXCEPTION_UNKNOWN = {"999999", "unknown exception"};
    /**
     * 未知服务代码无错误
     */
    public static String [] EXCEPTION_UNKNOWN_SERVICE_CODE = {"999998", "unknown service code exception"};
    /**
     * 并发数过高错误
     */
    public static String [] EXCEPTION_CONCURRENT = {"999997", "the number of concurrent too high"};

    /**
     * 重复请求
     */
    public static String [] EXCEPTION_REPEAT_REQUEST = {"999996", "repeat request"};

    /**
     * 无权限
     */
    public static String [] EXCEPTION_NO_PERMISSION = {"999995", "no permission"};

    /**
     * 未找到服务
     */
    public static String [] EXCEPTION_NO_SERVICE = {"999994", "no service"};


    /******************************************************************************************/

    /**
     * 数据格式错误
     */
    public static String ERROR_CODE_DATA_FORMAT = "999899";

}
