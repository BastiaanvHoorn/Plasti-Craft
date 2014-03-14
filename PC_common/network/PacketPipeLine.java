package network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class PacketPipeLine extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket> {

	private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();
	@Override
	protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
		ByteBuf buffer = Unpooled.buffer();
		Class<? extends AbstractPacket> cls = msg.getClass();
		if(!this.packets.contains(msg.getClass())){
			throw new NullPointerException("No packet registered for: " + msg.getClass().getCanonicalName());
		}
		
		byte discriminator = (byte) this.packets.indexOf(cls);
		buffer.writeByte(discriminator);
		msg.encodeInto(ctx, buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
	
	}

}
