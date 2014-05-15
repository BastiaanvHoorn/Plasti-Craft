package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import plasticraft.PlastiCraft;
import plasticraft.entity.EntityClone;

public class ContainerClone extends Container {

	private static EntityClone e;
	
	public ContainerClone(InventoryPlayer player, EntityClone entity) {
		e = entity;
		this.addSlotToContainer(new Slot((IInventory)entity,0, 0, 0));
		this.addSlotToContainer(new Slot((IInventory)entity,1, 16, 0));
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(player, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(player,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		PlastiCraft.info(entity.getStackInSlot(0) == null);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return e.isUseableByPlayer(var1);
	}

}
