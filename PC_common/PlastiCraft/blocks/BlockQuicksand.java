package PlastiCraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import PlastiCraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockQuicksand extends Block {
		
	
	    public BlockQuicksand (int id, Material material) {
	    	super(id, material);
	        setHardness(0.5F);
	        setStepSound(Block.soundGravelFootstep);
	        setLightValue(0.1F);
	        
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public static Icon TopIcon;
	    @SideOnly(Side.CLIENT)
	    public static Icon BottomIcon;
	    @SideOnly(Side.CLIENT)
	    public static Icon SideIcon;
	    
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void registerIcons(IconRegister icon){
	    	TopIcon= icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand_top");
	    	BottomIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand");
	    	SideIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand_side");
	    }
	    
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Icon getIcon(int Side, int Metadata){
	    	if(Side == 0){
	    		return BottomIcon;
	    	} else if(Side == 1){
	    		return TopIcon;
	    	} else{
	    		return SideIcon;
	    	}
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



		}
		
