package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
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
			case 3:
				if(te != null && te instanceof TeTrashCan){
					return new GuiTrashCan(player.inventory, (TeTrashCan)te);
				}
		}
		return null;
	}

}
