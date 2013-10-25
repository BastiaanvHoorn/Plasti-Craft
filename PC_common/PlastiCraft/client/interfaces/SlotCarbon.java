package plasticraft.client.interfaces;

import plasticraft.Carbon;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCarbon extends Slot{
	public SlotCarbon(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return Carbon.isCarbon(stack);
	}

}
