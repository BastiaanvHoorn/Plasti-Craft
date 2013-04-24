package PlastiCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import PlastiCraft.CommonProxy;
import PlastiCraft.PlastiCraft;

public class BlockQuicksand extends Block {
		
	
	    public BlockQuicksand (int id, Material material) {
	    	super(id, PlastiCraft.QuicksandMaterial);
	        setHardness(0.5F);
	        setStepSound(Block.soundGravelFootstep);
	        setLightValue(0.1F);
	        setCreativeTab(PlastiCraft.tabsPC);
	        setUnlocalizedName("Quicksand");
	        
	    }
	    
	    @Override
	    

	    
		public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
		{
			par5Entity.setInWeb();
		}
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
		{
			return null;
		}
		
		public Icon getBlockTextureFromSideAndMetadata(int par1, int par2){
		    return blockIcon;
		}
		public void registerIcons(IconRegister par1IconRegister){
		    blockIcon = par1IconRegister.registerIcon("Quicksand");
		}
		
}