package plasticraft.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import plasticraft.Carbon;
import plasticraft.PlastiCraft;

public class TileEntityCarbonFormer extends TileEntity implements ISidedInventory, IFluidHandler{

	public static FluidTank tank = new FluidTank(16000);
	
	private ItemStack[] items;
	
	private int[] Slots_bottom = {2};
	private int[] Slots_top = {0};
	private int[] Slots_side = {0,1};
	
	public TileEntityCarbonFormer(){
		items = new ItemStack[3];
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
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
				setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
				onInventoryChanged();
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
			itemstack.stackSize = getInventoryStackLimit();
			items[i] = itemstack;
		}
		
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Carbon Former";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i==0){
			return plasticraft.Carbon.isCarbon(itemstack);
		}else if(i == 1){
			if(TileEntityFurnace.isItemFuel(itemstack)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		
		NBTTagList items = new NBTTagList();
		
		for(int x=0; x < getSizeInventory();x++){
			
			ItemStack stack = getStackInSlot(x);
			
			if(stack!=null){
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("slot", (byte)x);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("items", items);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		
		NBTTagList items = compound.getTagList("items");
		
		for(int i = 0; i < items.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
			int slot = item.getByte("slot");
			
			if(slot >= 0 && slot < getSizeInventory()){
				setInventorySlotContents(slot,ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
	private static int timer = 0;
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
		ItemStack stack0 = getStackInSlot(0);
		ItemStack stack1 = getStackInSlot(1);
		if(!worldObj.isRemote){
			if(Carbon.isCarbon(stack0) && TileEntityFurnace.isItemFuel(stack1)){
				if(timer ==40){
					if(tank.getFluid()!= null){
						if(tank.getCapacity() - tank.getFluidAmount() >= 750){
							decrStackSize(0, 1);
							decrStackSize(1,1);
							tank.fill(new FluidStack(PlastiCraft.plastic_fluid, 750), true);
							this.onInventoryChanged();
							timer = 0;
						}else{
							timer = 0;
						}
						}else{
							decrStackSize(0, 1);
							decrStackSize(1,1);
							tank.fill(new FluidStack(PlastiCraft.plastic_fluid, 750), true);
							timer = 0;
							this.onInventoryChanged();
						}
					}else{
						timer++;
					}
					}else{
						timer = 0;
					}
				}
			}
		}
	

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		if (var1 == 0){
			return Slots_bottom;
		}else if(var1 == 1){
			return Slots_top;
		}else{
			return Slots_side;
		}
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		if(i == 2){
			return true;
		}else{
			return false;
		}
	}

	public static int getTime() {
		return timer;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}


	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {tank.getInfo()};
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(doDrain){
		tank.drain(tank.getFluidAmount(), true);
		return new FluidStack(PlastiCraft.plastic_fluid, tank.getFluidAmount());
		}else{
			return new FluidStack(PlastiCraft.plastic_fluid, tank.getFluidAmount());
		}
	}




}
