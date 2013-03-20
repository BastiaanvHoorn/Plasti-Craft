package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.CommonProxy;

public class block_Quicksand extends Block {

	
	   public block_Quicksand (int id, int texture, Material material) {
	        super(id, texture, material);
	        setHardness(0.5F);
	        setStepSound(Block.soundGravelFootstep);
	        setBlockName("Quicksand1");
	        setLightOpacity(1);
	        setLightValue(1);
	    }
	        
	    @Override
	    public String getTextureFile () {
	    	return CommonProxy.TEXTURES_PNG;
	    }
	    
	    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	    {
	        par5Entity.setInWeb();
	    }
	    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	    {
	        return null;
	    }
}