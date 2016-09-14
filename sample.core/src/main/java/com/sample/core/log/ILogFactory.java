package com.sample.core.log;

/**
 * Created by andongxu on 16-7-28.
 */
public interface ILogFactory {

    public Log getLog (Class<?> c);

    public Log getLog (String name);
}
