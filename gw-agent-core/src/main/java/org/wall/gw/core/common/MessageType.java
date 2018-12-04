package org.wall.gw.core.common;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public enum MessageType {

    SEND_TO_LOCAL_AGENT(1),
    SEND_TO_REMOTE_AGENT(2),
    SEND_TO_LOCAL_SERVICE(3),
    RESPONSE_TO_LOCAL_AGENT(4),
    RESPONSE_TO_REMOTE_AGENT(5),
    RESPONSE_TO_LOCAL_SERVICE(6),
    ;
    private int type;

    public int getType() {
        return type;
    }

    MessageType(int type) {
        this.type = type;
    }

    public static MessageType valueOfType(int type) {
        if (type == 1) return SEND_TO_LOCAL_AGENT;
        if (type == 2) return SEND_TO_REMOTE_AGENT;
        if (type == 3) return SEND_TO_LOCAL_SERVICE;
        if (type == 4) return RESPONSE_TO_LOCAL_AGENT;
        if (type == 5) return RESPONSE_TO_REMOTE_AGENT;
        if (type == 6) return RESPONSE_TO_LOCAL_SERVICE;
        return null;
    }

    @Override
    public String toString() {
        return "MessageType{" +
                "type=" + type +
                '}';
    }
}
