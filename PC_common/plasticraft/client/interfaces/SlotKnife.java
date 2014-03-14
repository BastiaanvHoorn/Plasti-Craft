package plasticraft.client.interfaces;

import plasticraft.items.PCItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotKnife extends Slot{

	public SlotKnife(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return stack.getItem().equals(PCItems.knife);
	}

}
