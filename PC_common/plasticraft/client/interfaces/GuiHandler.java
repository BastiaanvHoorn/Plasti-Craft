package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.items.Items;
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
		switch(ID){
			case 0:
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if(te != null && te instanceof TileEntityCarbonFormer){
					return new ContainerCarbonformer(player.inventory, (TileEntityCarbonFormer)te);
				}
			break;
			case 1:
				PlastiCraft.info(player.getCurrentEquippedItem().itemID == Items.lunchBox.itemID ? "true": "false");
				if(player.getCurrentEquippedItem().itemID == Items.lunchBox.itemID){
					PlastiCraft.info(player.getCurrentEquippedItem().hasTagCompound() ? "true": "false");
					if(player.getCurrentEquippedItem().hasTagCompound()){
						NBTTagCompound compound = player.getCurrentEquippedItem().getTagCompound();
							this.tile = new TeLunchBox(player.getCurrentEquippedItem() ,compound);

					}else{
						NBTTagCompound newCompound = new NBTTagCompound();
						player.getCurrentEquippedItem().setTagCompound(newCompound);
						this.tile = new TeLunchBox(player.getCurrentEquippedItem(), newCompound);
					}

				}
				
				if(this.tile != null && this.tile instanceof TeLunchBox){
					PlastiCraft.info("container lunchbox send");
					return new ContainerLunchBox(player.inventory, this.tile);
				}else if(this.tile == null){
					PlastiCraft.info("null");
				}else{
					PlastiCraft.info("not lunchbox");
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
			case 1:
				if(player.getCurrentEquippedItem().itemID == Items.lunchBox.itemID){
					if(player.getCurrentEquippedItem().hasTagCompound()){
						NBTTagCompound compound = player.getCurrentEquippedItem().getTagCompound();
							this.tile = new TeLunchBox(player.getCurrentEquippedItem(),compound);

					}else{
						NBTTagCompound newCompound = new NBTTagCompound();
						player.getCurrentEquippedItem().setTagCompound(newCompound);
						this.tile = new TeLunchBox(player.getCurrentEquippedItem(), newCompound);
					}

				}
				if(this.tile != null && this.tile instanceof TeLunchBox){
					PlastiCraft.info("Gui lunchbox send");
					return new GuiLunchBox(player.inventory, this.tile);
				}else if(this.tile == null){
					PlastiCraft.info("null");
				}else{
					PlastiCraft.info("not lunchbox");
				}
				break;
		}
		return null;
	}

}
