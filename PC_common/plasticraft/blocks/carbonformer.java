package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class carbonformer extends BlockContainer{

	
	private boolean isActive;
	private static boolean keepInventory;
	
	public carbonformer(int id, Material Material,int amount, boolean state) {
		super(id, Material);
		setStepSound(soundStoneFootstep);
		setHardness(2F);
		setLightValue(0.1F);
		this.isActive = state;
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
    	FrontIcon = this.isActive ? icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_front_on") : icon.registerIcon(References.MOD_ID.toLowerCase() +":carbonformer_front_off");
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
    
    @Override
    public boolean onBlockActivated(World world,int x,int y,int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		
    	if(player.isSneaking()){
    		return false;
    	}
    	
    	if(!world.isRemote){
    		FMLNetworkHandler.openGui(player, PlastiCraft.instance, 0, world, x, y, z);
    	}
    	
    	return true;
    	
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCarbonFormer();
	}
	
	@Override
	public void breakBlock(World world, int x,int y,int z,int id, int metadata){
		if(!keepInventory){
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof IInventory){
				IInventory inv = (IInventory)te;
				
				for(int i = 0; i < inv.getSizeInventory();i++){
					ItemStack item = inv.getStackInSlotOnClosing(i);
					
					if(item != null){
						float spawnx = x + world.rand.nextFloat();
						float spawny = y + world.rand.nextFloat();
						float spawnz = z + world.rand.nextFloat();
						
						EntityItem droppedItem = new EntityItem(world,spawnx,spawny,spawnz,item);
						
						float mult = 0.05F;
						
						droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
						droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
						droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;
						
						world.spawnEntityInWorld(droppedItem);
					}
				}
			}
		}
		
		super.breakBlock(world, x, y, z, id, metadata);
	}

    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        keepInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, Blocks.carbon_former_burning.blockID);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, Blocks.carbon_former_idle.blockID);
        }

        keepInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setBlockTileEntity(par2, par3, par4, tileentity);
        }
    }

}
