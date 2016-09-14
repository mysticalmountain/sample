package com.sample.core.log;

/**
 * Created by andongxu on 16-7-28.
 */
public class Log4jLogFactory implements ILogFactory {
    @Override
    public Log getLog(Class<?> c) {
        return new Log4jLog(c);
    }

    @Override
    public Log getLog(String name) {
        return new Log4jLog(name);
    }
}
