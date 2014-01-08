package network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import plasticraft.client.interfaces.ContainerGrindStone;
import plasticraft.tileentities.TeGrindStone;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{
	
	//use this class to send custom data to the server

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		
		EntityPlayer user = (EntityPlayer)player;
		
		byte packetID = reader.readByte();
		
		switch(packetID){
			case 0:
				byte type = reader.readByte();
				switch(type){
				case 2:
					if(reader.readByte() == 0){
						Container container = user.openContainer;
						if(container != null && container instanceof ContainerGrindStone){
							TeGrindStone te = ((ContainerGrindStone)container).getTe();
							te.Activate(user);
						}
					}
					break;
				}
			break;
		}
	}
	
	public static void sendInterfacePacket(byte type, byte button){
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try{
			dataStream.writeByte((byte)0);
			dataStream.writeByte(type);
			dataStream.writeByte(button);
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("pc", byteStream.toByteArray()));
		}catch(IOException e){
			System.err.append("failed to send packet");
		}
	}

}
