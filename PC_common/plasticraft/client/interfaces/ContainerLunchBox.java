package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TeLunchBox;

public class ContainerLunchBox extends Container{

	private InventoryPlayer player;
	private TeLunchBox tile;
	
	public ContainerLunchBox(InventoryPlayer player, TeLunchBox tile) {
		this.player = player;
		this.tile = tile;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(this.player, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(this.player,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		if(tile instanceof IInventory){
			addSlotToContainer(new Slot((IInventory)tile, 0, 80, 29));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public void removeCraftingFromCrafters(ICrafting crafting){
		this.tile.saveToItem();
		PlastiCraft.info("saved item (called)");
		super.removeCraftingFromCrafters(crafting);
		
	}

}
