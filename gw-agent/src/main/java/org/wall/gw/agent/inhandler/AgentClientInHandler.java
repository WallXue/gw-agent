package org.wall.gw.agent.inhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.wall.gw.core.common.MessageType;
import org.wall.gw.core.exception.AgentException;
import org.wall.gw.core.message.BusyMessageWrapper;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class AgentClientInHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BusyMessageWrapper busyMessageWrapper = new BusyMessageWrapper();
        busyMessageWrapper.setType(MessageType.SEND_TO_LOCAL_AGENT.getType());
        busyMessageWrapper.setInfo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbfffffffffffffffffffffff9");
        ctx.writeAndFlush(busyMessageWrapper);
        System.out.println("agent client 已连接到agent...");
    }

    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //todo 重连
        System.out.println("agent client 与agent已断开...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("agent client 信息接收完毕...");
    }

    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
//        ctx.close();
    }

    /**
     * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object info) throws Exception {
        BusyMessageWrapper msg = (BusyMessageWrapper) info;
        System.out.println("我是Agent Client，我接受到了：" + msg);
        MessageType messageType = MessageType.valueOfType(((BusyMessageWrapper) info).getType());
        if (messageType == null) {
            throw new AgentException("未知消息类型");
        }
        //agent client 被动接收两种类型的数据
        //一种是远程agent返回的信息
        //一种是本地service的返回的信息
        if (messageType == MessageType.RESPONSE_TO_REMOTE_AGENT) {
            //获取注册中心 缓存的 remote service=》agent信息，hash 或者 随机 或者轮训 选取一个agent
            //封装消息，并转换为FROM_AGENT 类型
            //调用远程agent
            //获取结果返回本地请求
            //
        } else if (messageType == MessageType.RESPONSE_TO_LOCAL_AGENT) {
            //获取 local service =》 chanel 信息 ，hash 或者 随机 或者轮训 选取一个agent
            //封装消息 并转换为FROM_AGENT
            //调用本地service
            //获取结果返回
        } else {
            //忽略其他类型消息
        }
    }

}
