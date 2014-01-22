package plasticraft.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TeTrashCan;

public class BlockTrashCan extends BlockContainer {

	public BlockTrashCan(int id, Material material) {
		super(id, material);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("trashCan");
		setHardness(3F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TeTrashCan();
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

}
