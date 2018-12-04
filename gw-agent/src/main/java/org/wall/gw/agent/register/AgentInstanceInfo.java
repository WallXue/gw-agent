package org.wall.gw.agent.register;

import io.netty.channel.socket.SocketChannel;

import java.util.Objects;
import java.util.Set;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class AgentInstanceInfo {

    // 持有服务
    public Set<String> holdingService;

    public String host;

    public int port;

    // 本agent 与远程agent连接
    public SocketChannel socketChannel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentInstanceInfo that = (AgentInstanceInfo) o;
        return port == that.port &&
                Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
