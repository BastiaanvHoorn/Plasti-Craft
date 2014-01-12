package plasticraft.client.interfaces;

import plasticraft.items.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BoxInventory extends SimpleInventory {
	private ItemStack boxOriginal;
	private EntityPlayer playerOriginal;
	private boolean isLoading = false;
	public BoxInventory(EntityPlayer player, ItemStack box) {
		super(1, "lunchbox", 64);
		boxOriginal = box;
		playerOriginal = player;
		loadInventory();
	}
	public void loadInventory() {
		this.load(boxOriginal.getTagCompound());
	}
	
	public void saveInventory() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.save(nbt);
		boxOriginal.setTagCompound(nbt);
		refreshNBT();
	}
	private void refreshNBT() {
		ItemStack held = playerOriginal.getHeldItem();
		if(held != null && held.itemID == Items.lunchBox.itemID){
			held.setTagCompound(boxOriginal.getTagCompound());
		}
		
	}
	
	@Override
	public void closeChest(){
		saveInventory();
	}
	
	@Override
	public void onInventoryChanged(){
		super.onInventoryChanged();
		if(!isLoading){
			saveInventory();
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack){
		if(stack.itemID == Items.steak.itemID)
			return true;
		return false;
	}

}
