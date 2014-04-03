package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import plasticraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockQuicksand extends Block {
		
	
	    public BlockQuicksand (Material material) {
	    	super(material);
	        setHardness(0.5F);
	        setStepSound(Block.soundTypeGravel);
	        setLightLevel(1.0F);
	        
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public static IIcon TopIcon;
	    @SideOnly(Side.CLIENT)
	    public static IIcon BottomIcon;
	    @SideOnly(Side.CLIENT)
	    public static IIcon SideIcon;
	    
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister icon){
	    	TopIcon= icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand_top");
	    	BottomIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand");
	    	SideIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":quicksand_side");
	    }
	    
	    @Override
	    @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int Side, int Metadata){
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
		
