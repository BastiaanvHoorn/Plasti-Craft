package PlastiCraft.blocks;

import PlastiCraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class carbonformer extends Block {

	public carbonformer(int id, Material Material) {
		super(id, Material);
		setStepSound(soundStoneFootstep);
		setHardness(2F);
		setLightValue(0.1F);
	}
    @SideOnly(Side.CLIENT)
    public static Icon TopIcon;
    @SideOnly(Side.CLIENT)
    public static Icon BottomIcon;
    @SideOnly(Side.CLIENT)
    public static Icon SideIcon;
    @SideOnly(Side.CLIENT)
    public static Icon FrontIcon;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon){
    	TopIcon= icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_top");
    	BottomIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer");
    	SideIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_side");
    	FrontIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_front");
    }
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }
    
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int Side, int Metadata){
    	if(Side == 0){
    		return BottomIcon;
    	} else if(Side == 1){
    		return TopIcon;
    	} else if(Side != Metadata){
    		return SideIcon;
    	}else{
    		return FrontIcon;
    	}
    }

}
