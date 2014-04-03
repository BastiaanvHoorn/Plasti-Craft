package plasticraft.client.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import plasticraft.Carbon;

public class SlotCarbon extends Slot{
	public SlotCarbon(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return Carbon.isCarbon(stack);
	}

}
