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
		super(material);
		setCreativeTab(PlastiCraft.tabsPC);
		setHardness(3F);
	}
	
	@Override
	public boolean onBlockActivated(World world,int x,int y,int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		if(player.isSneaking()){
			return false;
		}
		
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, PlastiCraft.instance, 3, world, x, y, z);
			return true;
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TeTrashCan();
	}

}
