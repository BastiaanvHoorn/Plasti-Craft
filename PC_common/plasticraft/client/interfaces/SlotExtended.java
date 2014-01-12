package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotExtended extends Slot{
	protected boolean canRemove = true;
	protected boolean canPlace = true;
	protected boolean enableGhosting = false;
	protected int limit = inventory.getInventoryStackLimit();
	protected ISlotController check;
	
	public SlotExtended(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	public SlotExtended setCheck(ISlotController c){
		check = c;
		return this;
	}

	public SlotExtended setRemoval(boolean flag) {
		canRemove = flag;
		return this;
	}
	
	@Override
	public int getSlotStackLimit(){
		return limit;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return canPlace && (check == null || check.canPlace(this, stack));
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player){
		return canRemove && (check == null || check.canTake(this));
	}

}
