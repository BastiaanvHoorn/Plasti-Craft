package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerClone extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return false;
	}

}
