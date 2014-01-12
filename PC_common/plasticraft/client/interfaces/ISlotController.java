package plasticraft.client.interfaces;

import net.minecraft.item.ItemStack;

public interface ISlotController {
	public boolean canTake(SlotExtended slot);
	
	public boolean canPlace(SlotExtended slot, ItemStack stack);
	
	public static class InventoryRulesController implements ISlotController{
		
		public static InventoryRulesController instance = new InventoryRulesController();

		@Override
		public boolean canTake(SlotExtended slot) {
			return true;
		}

		@Override
		public boolean canPlace(SlotExtended slot, ItemStack stack) {
			return slot.inventory.isItemValidForSlot(slot.slotNumber, stack);
		}
		
	}
}
