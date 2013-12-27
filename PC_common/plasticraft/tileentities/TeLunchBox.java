package plasticraft.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import plasticraft.PlastiCraft;
import plasticraft.items.Items;

public class TeLunchBox extends TileEntity implements IInventory{

	private ItemStack stack;
	private ItemStack item;
	private NBTTagCompound compound;
	
	public TeLunchBox(ItemStack stack, NBTTagCompound containing){
		this.stack = stack;
		this.compound = containing;
		this.readFromNBT(containing);

		
		
	}
	

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if(i == 0){
		return this.item;
		}else{
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= j){
				this.setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(j);
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
			this.item = itemstack;
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Lunch Box";
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
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack != null){
			if(i == 0 && Items.plastic_Item.itemID == itemstack.itemID){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound){
		NBTTagList items = new NBTTagList();
		
		for(int x=0; x < getSizeInventory();x++){
			
			ItemStack stackInSlot = getStackInSlot(x);
			
			if(stackInSlot!=null){
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("slot", (byte)x);
				stackInSlot.writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("items", items);
		PlastiCraft.info("item saved");
		super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
	NBTTagList items = compound.getTagList("items");
			
			for(int i = 0; i < items.tagCount(); i++){
				NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
				int slot = item.getByte("slot");
				
				if(slot >= 0 && slot < getSizeInventory()){
					this.setInventorySlotContents(slot,ItemStack.loadItemStackFromNBT(item));
					PlastiCraft.info("item loaded");
				}
			}
		super.readFromNBT(compound);
	}

	public void saveToItem() {
		this.writeToNBT(compound);
	}
	
}
