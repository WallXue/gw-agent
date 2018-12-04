package org.wall.gw.agent.register.remote;

import io.netty.channel.socket.SocketChannel;
import org.wall.gw.agent.register.AgentInstanceInfo;
import org.wall.gw.core.exception.AgentException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class RemoteAgentRegister {

    private static final ConcurrentHashMap<String, List<AgentInstanceInfo>> serviceMapping = new ConcurrentHashMap<String, List<AgentInstanceInfo>>();

    private static final Lock lock = new ReentrantLock();

    public static void registAgent(AgentInstanceInfo agentInstanceInfo) {
        try {
            lock.lock();
            if (agentInstanceInfo == null)
                throw new AgentException("agent 信息不能为空");

            //处理service mapping
            removeAgent(agentInstanceInfo);

            for (String serviceName : agentInstanceInfo.holdingService) {
                List<AgentInstanceInfo> agentInstanceInfos = serviceMapping.get(serviceName);
                if (agentInstanceInfos == null) {
                    agentInstanceInfos = new Vector<AgentInstanceInfo>();
                    serviceMapping.put(serviceName, agentInstanceInfos);
                }
                agentInstanceInfos.add(agentInstanceInfo);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void removeAgent(AgentInstanceInfo agentInstanceInfo) {
        try {
            lock.lock();

            for (Map.Entry<String, List<AgentInstanceInfo>> stringListEntry : serviceMapping.entrySet()) {
                List<AgentInstanceInfo> value = stringListEntry.getValue();
                if (value != null) {
                    Iterator<AgentInstanceInfo> iterator = value.iterator();
                    while (iterator.hasNext()) {
                        AgentInstanceInfo next = iterator.next();
                        if (next.equals(agentInstanceInfo)) {
                            iterator.remove();
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }

    }

    public static AgentInstanceInfo selectAgent(String serviceName) {
        try {
            lock.lock();
            List<AgentInstanceInfo> agentInstanceInfos = serviceMapping.get(serviceName);
            if (agentInstanceInfos == null || agentInstanceInfos.isEmpty())
                return null;
            //todo 需要改为轮训或其他机制 达到负载均衡
            return agentInstanceInfos.get(0);
        } finally {
            lock.unlock();
        }
    }
}
