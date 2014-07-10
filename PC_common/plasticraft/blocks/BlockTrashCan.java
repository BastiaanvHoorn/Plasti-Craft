package plasticraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TeTrashCan;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class BlockTrashCan extends BlockContainer {

	public BlockTrashCan(Material material) {
		//setting variables
		super(material);
		setCreativeTab(PlastiCraft.tabsPC);
		setHardness(3F);
	}
	
	@Override//gets called whet you click on the block
	public boolean onBlockActivated(World world,int x,int y,int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		if(player.isSneaking()){
			return false;
		}
		//check if you're on the server side
		if(!world.isRemote){
			//open the inventory
			FMLNetworkHandler.openGui(player, PlastiCraft.instance, 3, world, x, y, z);
			//return true because you did do something
			return true;
		}
		//return true by default so you don't get a desync
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		//creates an tileentity for every block
		return new TeTrashCan();
	}

}
