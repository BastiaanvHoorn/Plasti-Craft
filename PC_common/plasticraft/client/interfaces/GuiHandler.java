package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.items.Items;
import plasticraft.items.LunchBox;
import plasticraft.tileentities.TeGrindStone;
import plasticraft.tileentities.TeLunchBox;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler{

	private TeLunchBox tile;
	
	public GuiHandler(){
		NetworkRegistry.instance().registerGuiHandler(PlastiCraft.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			case 0:
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new ContainerCarbonformer(player.inventory, (TileEntityCarbonFormer)te);
				}
			break;
			case 1:
				PlastiCraft.info(player.getCurrentEquippedItem().itemID == Items.lunchBox.itemID ? "true": "false");
				if(player.getCurrentEquippedItem().itemID == Items.lunchBox.itemID){
					return LunchBox.getContainer(player);
				}
				break;
			case 2:
				return new ContainerGrindStone(player.inventory, (TeGrindStone)te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			case 0:
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new GuiCarbonFormer(player.inventory,(TileEntityCarbonFormer)te);
				}
			break;
			case 1:
				ItemStack held = player.getHeldItem();
				if(held.itemID == Items.lunchBox.itemID){
					return new GuiLunchBox(player, LunchBox.getBoxInventory(player), held);
				}
				break;
			case 2:
				return new GuiGrindStone(player.inventory, (TeGrindStone)te);
		}
		return null;
	}

}
