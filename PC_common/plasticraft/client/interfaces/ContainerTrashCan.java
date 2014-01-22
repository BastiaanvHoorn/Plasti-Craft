package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import plasticraft.tileentities.TeTrashCan;

public class ContainerTrashCan extends Container {
	
	private TeTrashCan te;
	
	public ContainerTrashCan(IInventory inv, TeTrashCan te){
		this.te = te;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(inv, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(inv,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		
		addSlotToContainer(new Slot(te, 0, 80 ,29));
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i){
		return null;
	}

}
