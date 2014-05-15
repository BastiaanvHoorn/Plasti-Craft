package network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import cpw.mods.fml.common.network.ByteBufUtils;
import plasticraft.PlastiCraft;
import plasticraft.entity.EntityClone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class ClonePickupPacket extends AbstractPacket {
	
	private ItemStack stack;
	private int cloneID, slot;
	
	public ClonePickupPacket(){
		
	}
	
	public ClonePickupPacket(ItemStack stack, int cloneID, int slot){
		this.stack = stack;
		this.cloneID = cloneID;
		this.slot = slot;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(cloneID);
		buffer.writeInt(slot);
		ByteBufUtils.writeItemStack(buffer, stack);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		cloneID = buffer.readInt();
		slot = buffer.readInt();
		stack = ByteBufUtils.readItemStack(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		Entity e = player.worldObj.getEntityByID(cloneID);
		if(e instanceof EntityClone){
			((EntityClone)e).setInventorySlotContents(slot, stack);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {

	}

}
