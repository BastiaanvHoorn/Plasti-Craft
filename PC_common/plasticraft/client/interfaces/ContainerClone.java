package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import plasticraft.entity.EntityClone;

public class ContainerClone extends Container {

	private static EntityClone e;
	
	public ContainerClone(InventoryPlayer player, EntityClone entity) {
		e = entity;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return e.isUseableByPlayer(var1);
	}

}
