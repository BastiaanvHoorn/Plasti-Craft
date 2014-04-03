package plasticraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.items.PCItems;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EntityClone extends EntityLiving implements IInventory{
	
	private boolean printed;
	private ItemStack[] items;
	
	@Override
	public boolean interact(EntityPlayer player){
		if(player.inventory.getCurrentItem().getItem().equals(PCItems.LocationSetter)){
			if(player.inventory.getCurrentItem().stackTagCompound != null){
				NBTTagCompound compound = player.inventory.getCurrentItem().stackTagCompound;
				this.setAIDestination((double)compound.getInteger("x"), (double)compound.getInteger("y"), (double)compound.getInteger("z"));
				return true;
			}
		}
		if(!this.worldObj.isRemote && !player.inventory.getCurrentItem().getItem().equals(PCItems.LocationSetter)){
			FMLNetworkHandler.openGui(player, PlastiCraft.instance, 1, this.worldObj, this.getEntityId(), (int)this.posY, (int)this.posZ);
		}
		return true;
	}
	
	
	public EntityClone(World par1World) {
		super(par1World);
		items = new ItemStack[2];
	}
	
	@Override
	public boolean isAIEnabled(){
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		if(getPrinted()){
			return 2;
		}
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= count){
				this.setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		this.setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		items[i] = stack;
		
		if(stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = this.getInventoryStackLimit();
			this.items[i] = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return "Clone";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.isDead ? false : entityplayer.getDistanceSqToEntity(this) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}
	
	public void setPrinted(boolean flag){
		this.printed = flag;
	}
	
	public boolean getPrinted(){
		return printed;
	}
	
	public void setAIDestination(double x, double y, double z){
		this.getNavigator().tryMoveToXYZ(x, y, z, 0.9D);
	}
	
}
