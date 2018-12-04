package org.wall.gw.core.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.wall.gw.core.message.BusyMessageWrapper;

import java.util.List;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class BusyMessageWrapperDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //标记开始读取位置
        in.markReaderIndex();
        //判断协议类型
        int infoType = in.readInt();
        BusyMessageWrapper msg = new BusyMessageWrapper();
        System.out.println(infoType);
        msg.setType(infoType);
        //in.readableBytes()即为剩下的字节数
        byte[] info = new byte[in.readableBytes()];
        in.readBytes(info);
        msg.setInfo(new String(info, "utf-8"));
        System.out.println(info.length + " : " + msg.getInfo());
        //最后把你想要交由ServerHandler的数据添加进去，就可以了
        out.add(msg);
    }
}
