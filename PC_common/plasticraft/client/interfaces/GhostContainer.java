package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class GhostContainer extends Container{

	protected IInventory playerInv;
	
	boolean canInteract = true;
	
	public GhostContainer(IInventory inv){
		this.playerInv = inv;
	}
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return canInteract;
	}
	public GhostContainer addCustomSlot(SlotExtended s) {
		
		addSlotToContainer(s);
		return this;
	}
	public GhostContainer addPlayerInventory(int i, int j) {
		if(playerInv == null)
			return this;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new SlotExtended(playerInv, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new SlotExtended(playerInv,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		
		return this;
	}

}
