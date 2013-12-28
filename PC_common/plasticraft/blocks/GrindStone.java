package plasticraft.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GrindStone extends Block {

	public GrindStone(int id) {
		super(id, Material.anvil);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("GrindStone");
		setTextureName(References.MOD_ID +":grindStone");
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		
    	if(player.isSneaking()){
    		return false;
    	}
    	
    	if(!world.isRemote){
    		FMLNetworkHandler.openGui(player, PlastiCraft.instance, 0, world, x, y, z);
    		PlastiCraft.info("Opening Gui");
    	}
    	
    	return true;
    	
    }

}
