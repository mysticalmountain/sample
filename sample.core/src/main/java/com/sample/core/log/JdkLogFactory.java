package com.sample.core.log;

/**
 * Created by andongxu on 16-7-28.
 */
public class JdkLogFactory implements ILogFactory {
    @Override
    public Log getLog(Class<?> c) {
        return new JdkLog(c);
    }

    @Override
    public Log getLog(String name) {
        return new JdkLog(name);
    }
}
