package plasticraft.client.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import plasticraft.items.Items;

public class SlotGrindStone extends Slot{

	public SlotGrindStone(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return stack.itemID == Items.knife.itemID;
	}

}
