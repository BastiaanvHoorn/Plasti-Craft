package plasticraft.client.interfaces;

import plasticraft.entity.EntityClone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

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
