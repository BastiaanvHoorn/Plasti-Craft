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
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import plasticraft.Carbon;
import plasticraft.PlastiCraft;
import plasticraft.blocks.carbonformer;
import plasticraft.fluid.CarbonTank;

public class TileEntityCarbonFormer extends TileEntity implements ISidedInventory, IFluidHandler{

	public CarbonTank tank;
	
	private ItemStack[] items;
	
	public int burnTime;
	public int cookTime;
	
	private int[] Slots_bottom = {2};
	private int[] Slots_top = {0};
	private int[] Slots_side = {0,1};
	
	public TileEntityCarbonFormer(){
		this.items = new ItemStack[2];
		this.tank = new CarbonTank(16000, this);
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= count){
				this.setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
				this.onInventoryChanged();
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
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.items[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
			this.items[i] = itemstack;
		}
		
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Carbon Former";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
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
		compound.setShort("burnTime", (short)this.burnTime);
		compound.setShort("cookTime", (short)this.cookTime);
		
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
	
		compound.setInteger("tankAmount", this.tank.getFluidAmount());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		if(compound.getInteger("tankAmount") != 0){
			this.tank.fill(new FluidStack(PlastiCraft.plastic_fluid, compound.getInteger("tankAmount")), true);
		}
		
		NBTTagList items = compound.getTagList("items");
		
		for(int i = 0; i < items.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
			int slot = item.getByte("slot");
			
			if(slot >= 0 && slot < getSizeInventory()){
				setInventorySlotContents(slot,ItemStack.loadItemStackFromNBT(item));
			}
		}
		
		this.cookTime = compound.getShort("cookTime");
		this.burnTime = compound.getShort("burnTime");
	}
	
	public boolean canSmelt(){
		if(this.items[0] == null || this.tank.getFluidAmount() >= (this.tank.getCapacity() - 750)){
			return false;
		}else{
			FluidStack fluid = this.tank.getFluid();
			if(fluid != null){
				if(Carbon.isCarbon(this.items[0]) && fluid.getFluid() == PlastiCraft.plastic_fluid) return true;
			}else{
				return Carbon.isCarbon(this.items[0]);
			}
			return false;
		}
	}
	
	public int getCookProgressScaled(int par1){
		return this.cookTime * par1 / 200;
	}
	
	public int getFluidAmountScaled(int par1){
		return this.tank.getFluidAmount() * par1 / 16000;
	}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	@Override
	public void updateEntity(){
		
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		
		if(this.burnTime > 0){
			--this.burnTime;
		}
		if(!this.worldObj.isRemote){
			if(this.burnTime == 0 && this.canSmelt()){
				this.burnTime = TileEntityFurnace.getItemBurnTime(this.items[1]);
				
				if(this.burnTime > 0){
					flag1 = true;
					
					if(this.items[1] != null){
						--this.items[1].stackSize;
						if(this.items[1].stackSize == 0){
							this.items[1] = this.items[1].getItem().getContainerItemStack(items[1]);
						}
					}
				}
			}
			if(this.isBurning() && this.canSmelt()){
				this.cookTime += 1;
				
				if(this.cookTime == 200){
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}else{
				this.cookTime = 0;
			}
			
			if(flag != this.burnTime > 0){
				flag = true;
				carbonformer.updateFurnaceBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
			
		if(flag1){
			this.onInventoryChanged();
		}
			

	}
	
	public void smeltItem(){
		if(this.canSmelt()){
			FluidStack fluid = this.tank.getFluid();
			if(fluid != null){
				if(fluid.amount < (this.tank.getCapacity() -750)){
					this.tank.fill(new FluidStack(PlastiCraft.plastic_fluid, 750), true);
					--this.items[0].stackSize;
					if(this.items[0].stackSize <= 0){
						this.items[0] = null;
					}
				}
			}else{
				this.tank.fill(new FluidStack(PlastiCraft.plastic_fluid, 750), true);
				--this.items[0].stackSize;
				if(this.items[0].stackSize <= 0){
					this.items[0] = null;
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

	public int getTime() {
		return this.cookTime;
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
		tank.drain(resource.amount, true);
		return new FluidStack(PlastiCraft.plastic_fluid, tank.getFluidAmount());
		}else{
			return new FluidStack(PlastiCraft.plastic_fluid, tank.getFluidAmount());
		}
	}

	public CarbonTank getTank() {
		return this.tank;
	}





}
