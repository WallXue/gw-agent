package org.wall.gw.core.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.wall.gw.core.message.BusyMessageWrapper;

/**
 * @author Xuewu
 * @date 2018/12/4
 */
public class BusyMessageWrapperEncoder extends MessageToByteEncoder<BusyMessageWrapper> {

    protected void encode(ChannelHandlerContext channelHandlerContext, BusyMessageWrapper msg, ByteBuf byteBuf) throws Exception {
        ByteBufOutputStream writer = new ByteBufOutputStream(byteBuf);

        writer.writeInt(msg.getType());
        byte[] info = null;

        if (msg != null && msg.getInfo() != null && msg.getInfo() != "") {
            info = msg.getInfo().getBytes("utf-8");
            writer.write(info);
        }

    }

}
