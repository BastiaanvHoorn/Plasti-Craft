package plasticraft.tileentities;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import plasticraft.PlastiCraft;
import plasticraft.blocks.Blocks;
import plasticraft.blocks.GrindStone;
import plasticraft.items.Items;

public class TeGrindStone extends TileEntity implements ISidedInventory{

	private ItemStack[] items;
	private int[] Slots_bottom = {2};
	private int[] Slots_top = {0};
	private int[] Slots_side = {0, 1};
	
	public boolean isActive;
	private int nextDamage;
	
	public TeGrindStone()
	{
		this.items = new ItemStack[2];
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}
	
	@Override
	public void updateEntity()
	{
		if (this.getStackInSlot(0) != null && this.getStackInSlot(1) != null)
		{
			if (this.getStackInSlot(0).getItemDamage() != 0 && isActive)
			{
				++nextDamage;
				
				if (nextDamage == 10)
				{
					nextDamage = 0;
					
					ItemStack itemStack1 = this.getStackInSlot(0);
					itemStack1.setItemDamage(this.getStackInSlot(0).getItemDamage() - 1);
					this.setInventorySlotContents(0, itemStack1);
					
					ItemStack itemStack2 = this.getStackInSlot(1);
					itemStack2.setItemDamage(this.getStackInSlot(1).getItemDamage() + 1);
					this.setInventorySlotContents(1, itemStack2);
				}
			}
			else
			{
				deActivate();
			}
		}
		else
		{
			deActivate();
		}
	}
	
	public void deActivate()
	{
		if (isActive == true)
		{
			isActive = false;
			
			GrindStone.updateBlockState(false, worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	public void Activate(EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode)
		{
			ItemStack itemStack = this.getStackInSlot(0);
			itemStack.setItemDamage(0);
			this.setInventorySlotContents(0, itemStack);
		}
		else
		{
			isActive = true;
			
			GrindStone.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	public int getDurability()
	{
		if (this.getStackInSlot(0) != null)
		{
			return this.getStackInSlot(0).getMaxDamage() - this.getStackInSlot(0).getItemDamage();	
		}
		else
		{
			return 0;
		}
	}
	
	public int getDurability(int scale)
	{
		if (this.getStackInSlot(0) != null)
		{
			return (this.getStackInSlot(0).getMaxDamage() - this.getStackInSlot(0).getItemDamage()) * scale / 64;	
		}
		else
		{
			return 0;
		}
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
	public ItemStack getStackInSlot(int i)
	{
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
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		
		NBTTagList items = new NBTTagList();
		
		for(int x = 0; x < getSizeInventory(); x++){
			
			ItemStack stack = getStackInSlot(x);
			
			if (stack != null)
			{
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
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
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
		return true;//entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}
	
	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if (i == 0)
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
		else if (i == 1)
		{
			if (itemstack.itemID == Items.grindFrame.itemID)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
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
