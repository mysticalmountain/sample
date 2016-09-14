package com.sample.core.log;

/**
 * Created by andongxu on 16-8-22.
 */
public class LogInfo<D> {

    private String system;

    private String module;

    private String trans;

    private Direction direction;

    private D data;

    public LogInfo(String system, String module, String trans, Direction direction, D data) {
        this.system = system;
        this.module = module;
        this.trans = trans;
        this.direction = direction;
        this.data = data;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Direction {
        REQ,        //请求
        RSP;        //响应
    }
}
