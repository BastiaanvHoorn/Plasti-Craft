package PlastiCraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import PlastiCraft.tileentities.TileEntityCarbonFormer;

public class ContainerCarbonformer extends Container{

	private TileEntityCarbonFormer carbonformer;
	
	public ContainerCarbonformer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer){
		this.carbonformer = carbonformer;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		for(int x=0; x<3;x++){
			if(x==0){
				addSlotToContainer( new SlotDiamond(carbonformer,x,8+ 18 * x, 17));
			}else if(x == 1){
				addSlotToContainer(new SlotFuel(carbonformer,x,8+18*x,17));
			}else
			addSlotToContainer(new SlotNone(carbonformer,x, 8 + 18 * x, 17));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return carbonformer.isUseableByPlayer(entityplayer);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i){
		return null;
	}

}
