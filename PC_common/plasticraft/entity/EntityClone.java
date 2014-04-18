package plasticraft.entity;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
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
		if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().getItem().equals(PCItems.LocationSetter)){
				if(player.inventory.getCurrentItem().stackTagCompound != null){
					NBTTagCompound compound = player.inventory.getCurrentItem().stackTagCompound;
					this.setAIDestination((double)compound.getInteger("x"), (double)compound.getInteger("y"), (double)compound.getInteger("z"));
					return true;
				}
			}else if(player.inventory.getCurrentItem().getItem().equals(PCItems.BlockBreaker)){
				if(player.inventory.getCurrentItem().stackTagCompound != null){
					NBTTagCompound compound = player.inventory.getCurrentItem().stackTagCompound;
					this.breakBlock(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
					return true;
				}
			}
		}
		if(!this.worldObj.isRemote ){
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
	
	public void breakBlock(int x, int y, int z){
		if(!this.worldObj.isRemote){
			setAIDestination((double)x, (double)y, (double)z);
			if(this.worldObj.isAirBlock(x, y, z) == false){
				ArrayList<ItemStack> items = this.worldObj.getBlock(x, y, z).getDrops(this.worldObj, x, y, z, this.worldObj.getBlockMetadata(x, y, z), 0);
				PlastiCraft.info("arraylist gotten");
				for(int i = 0;i < items.size(); i++){
					ItemStack item = items.get(i);
					float spawnx = x + this.worldObj.rand.nextFloat();
					float spawny = y + this.worldObj.rand.nextFloat();
					float spawnz = z + this.worldObj.rand.nextFloat();
				
					EntityItem entity = new EntityItem(this.worldObj, spawnx, spawny, spawnz, item);
			
					float mult = 0.05F;
					
					entity.motionX = (-0.5F + this.worldObj.rand.nextFloat()) * mult;
					entity.motionY = (4 + this.worldObj.rand.nextFloat()) * mult;
					entity.motionZ = (-0.5F + this.worldObj.rand.nextFloat()) * mult;
				
					this.worldObj.spawnEntityInWorld(entity);
					PlastiCraft.info("item added");
				}
				PlastiCraft.info("Out of loop");
			}
			this.worldObj.setBlockToAir(x, y, z);
		}
	}
}
