package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SimpleInventory implements IInventory{

	private final String invName;
	private ItemStack[] items;
	private final int slotLimit;
	
	public SimpleInventory(int size, String name, int stackLimit){
		items = new ItemStack[size];
		invName = name;
		slotLimit = stackLimit;
	}
	public void load(NBTTagCompound tagCompound) {
		load(tagCompound, "");
		
	}
	
	public void load(NBTTagCompound tag, String string) {
		if(tag == null)
			return;
		NBTTagList tag1 = tag.getTagList(string + "items");
		
		for(int i = 0; i < tag1.tagCount(); i++){
			NBTTagCompound tag2 = (NBTTagCompound) tag1.tagAt(i);
			int index = tag2.getInteger("index");
			if(index < items.length){
				items[index] = ItemStack.loadItemStackFromNBT(tag2);
			}
			onInventoryChanged();
		}
		
	}
	
	public void save(NBTTagCompound nbt){
		save(nbt,"");
	}
	private void save(NBTTagCompound nbt, String string) {
		if(nbt == null)
			return;
		
		NBTTagList tag1 = new NBTTagList();
		for(int i = 0; i < items.length; i++){
			if(items[i] != null && items[i].stackSize >0){
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setInteger("index", i);
				items[i].writeToNBT(tag2);
				tag1.appendTag(tag2);
			}		
		}
		nbt.setTag(string + "items", tag1);
		nbt.setInteger(string + "itemsCount", items.length);
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
	public ItemStack decrStackSize(int i, int j) {
		if(items[i] == null){
			return null;
		}
		if(items[i].stackSize > j){
			ItemStack ret = items[i].splitStack(j);
			return ret;
		}
		ItemStack ret = items[i];
		items[i] = null;
		return ret;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.items[i] == null){
			return null;
		}
		ItemStack stackToTake = this.items[i];
		this.items[i] = null;
		onInventoryChanged();
		return stackToTake;
	}
	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items [i] = itemstack;
		
	}
	@Override
	public String getInvName() {
		return invName;
	}
	@Override
	public boolean isInvNameLocalized() {
		return true;
	}
	@Override
	public int getInventoryStackLimit() {
		return slotLimit;
	}
	@Override
	public void onInventoryChanged() {
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}
	@Override
	public void openChest() {}
	
	@Override
	public void closeChest() {}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

}
