package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TileEntityCarbonFormer;

public class GuiHandler implements IGuiHandler{

	public GuiHandler(){
		NetworkRegistry.instance().registerGuiHandler(PlastiCraft.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
			case 0:
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new ContainerCarbonformer(player.inventory, (TileEntityCarbonFormer)te);
				}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityCarbonFormer){
				return new GuiCarbonFormer(player.inventory,(TileEntityCarbonFormer)te);
			}
			break;
		}
		return null;
	}

}
