package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.items.PCItems;
import plasticraft.items.LunchBox;
import plasticraft.tileentities.TeGrindStone;
import plasticraft.tileentities.TeTrashCan;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler{
	
	public GuiHandler(){
		NetworkRegistry.INSTANCE.registerGuiHandler(PlastiCraft.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch(ID){
			case 0:
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new ContainerCarbonformer(player.inventory, (TileEntityCarbonFormer)te);
				}
			break;
			case 1:
				if(player.getCurrentEquippedItem().getItem().equals(PCItems.lunchBox)){
					return LunchBox.getContainer(player);
				}
				break;
			case 2:
				if(te != null && te instanceof TeGrindStone){
					return new ContainerGrindStone(player.inventory, (TeGrindStone)te);
				}
				break;
			case 3:
				if(te != null && te instanceof TeTrashCan){
					return new ContainerTrashCan(player.inventory, (TeTrashCan)te);
				}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch(ID){
			case 0:
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new GuiCarbonFormer(player.inventory,(TileEntityCarbonFormer)te);
				}
			break;
			case 1:
				ItemStack held = player.getHeldItem();
				if(held.getItem().equals(PCItems.lunchBox)){
					return new GuiLunchBox(player, LunchBox.getBoxInventory(player), held);
				}
				break;
			case 2:
				if(te != null && te instanceof TeGrindStone){
					return new GuiGrindStone(player.inventory, (TeGrindStone)te);
				}
				break;
			case 3:
				if(te != null && te instanceof TeTrashCan){
					return new GuiTrashCan(player.inventory, (TeTrashCan)te);
				}
		}
		return null;
	}

}
