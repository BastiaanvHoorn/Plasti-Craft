package plasticraft.tileentities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.blocks.Blocks;
import plasticraft.blocks.GrindStone;
import plasticraft.items.Items;

public class TeGrindStone extends TileEntity implements ISidedInventory{

	private ItemStack[] items;
	private int[] Slots_bottom = {2};
	private int[] Slots_top = {0};
	private int[] Slots_side = {0, 1};
	
	public int grindTime;
	
	public TeGrindStone()
	{
		this.items = new ItemStack[3];
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}
	
	@Override
	public void updateEntity()
	{
		if (this.items[2] == null)
		{
			if (this.items[0] != null)
			{
				if (this.items[0].getItemDamage() != 0)
				{
					if (this.grindTime > 0 && this.grindTime < this.items[0].getItemDamage() * 4)
					{
						++this.grindTime;
						
					}
					else if (this.grindTime >= this.items[0].getItemDamage() * 4)
					{
						resetGrindTime();

						this.setInventorySlotContents(0, null);
						this.setInventorySlotContents(1, new ItemStack(Items.knife));
					}
					else
					{
						resetGrindTime();
					}
				}
			}
			else
			{
				resetGrindTime();
			}
		}
		
		//GrindStone.updateBlockState(this.grindTime > 0, worldObj, xCoord, yCoord, zCoord);
	}
	
	public void resetGrindTime()
	{
		this.grindTime = 0;
	}
	
	public void creativeMode()
	{
		if (this.grindTime > 0)
		{
			this.grindTime = this.items[0].getItemDamage() * 4;
		}
	}
	
	public int getProgressScaled(int scale)
	{
		return this.grindTime * scale / (this.items[0].getItemDamage() * 4);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = getStackInSlot(i);
		
		if(itemstack != null)
		{
			if(itemstack.stackSize <= j)
			{
				this.setInventorySlotContents(i, null);
			}
			else
			{
				itemstack = itemstack.splitStack(j);
				this.onInventoryChanged();
			}
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.items[i];
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
		
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
			this.items[i] = itemstack;
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
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}
	
	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i == 0)
		{
			if (itemstack.itemID == Items.knife.itemID)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(i == 1)
		{
			if(itemstack.itemID == Block.obsidian.blockID)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		if (par1 == 0)
		{
			return Slots_bottom;
		}
		else if (par1 == 1)
		{
			return Slots_top;
		}
		else
		{
			return Slots_side;
		}
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return true;
	}
}
