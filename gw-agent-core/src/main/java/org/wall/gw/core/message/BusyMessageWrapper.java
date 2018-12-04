package org.wall.gw.core.message;

import com.alibaba.fastjson.JSON;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class BusyMessageWrapper {

    private String info;

    private int type;

    public void setInfo(String info) {
        this.info = info;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
