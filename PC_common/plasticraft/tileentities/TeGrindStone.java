package plasticraft.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import plasticraft.PlastiCraft;
import plasticraft.items.Items;

public class TeGrindStone extends TileEntity implements IInventory{

	private ItemStack stackInput;
	private ItemStack stackOutput;
	
	public int grindTime;
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i == 0)
		{
			return this.stackInput;
		}
		else if (i == 1)
		{
			return this.stackOutput;
		}
		
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= j){
				this.setInventorySlotContents(i, null);
			}
			else{
				itemstack = itemstack.splitStack(j);
				this.onInventoryChanged();
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (i == 0)
		{
			return this.stackInput;
		}
		else if (i == 1)
		{
			return this.stackOutput;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i == 0)
		{
			this.stackInput = itemstack;
		}
		else if (i == 1)
		{
			this.stackOutput = itemstack;
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Grindstone";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return itemstack != null && i == 0 && itemstack.itemID == Items.knife.itemID;
	}
}
