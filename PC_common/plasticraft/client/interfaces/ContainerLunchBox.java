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
