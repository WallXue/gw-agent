package org.wall.gw.agent;

import org.wall.gw.agent.client.AgentClient;
import org.wall.gw.agent.register.remote.RemoteAgentRegister;
import org.wall.gw.agent.server.AgentServer;

import java.util.HashSet;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class Bootstart {

    public static void startup() {
        //启动AgentServer
        new AgentServer().bind(5001);

        //获取注册中心信息
        System.out.println("获取注册中心信息");
        HashSet<String> sns = new HashSet<>();
        sns.add("s1");
        new AgentClient(5001, "127.0.0.1", sns).connect();

        //注册agent
        System.out.println("注册agent");
    }
}
