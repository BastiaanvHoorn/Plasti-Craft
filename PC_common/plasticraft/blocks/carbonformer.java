package plasticraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class carbonformer extends BlockContainer{

	
	private boolean isActive;
	private static boolean keepInventory;
	
	public carbonformer(Material Material,boolean state) {
		super(Material);
		setStepSound(Block.soundTypeStone);
		setHardness(2F);
		this.isActive = state;
	}

	
	
    @SideOnly(Side.CLIENT)
    public static IIcon TopIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon BottomIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon SideIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon FrontIcon_on;
    @SideOnly(Side.CLIENT)
    public static IIcon FrontIcon_off;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon){
    	TopIcon= icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_top");
    	BottomIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer");
    	SideIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_side");
    	FrontIcon_on = icon.registerIcon(References.MOD_ID.toLowerCase() + ":carbonformer_front_on");
    	FrontIcon_off = icon.registerIcon(References.MOD_ID.toLowerCase() +":carbonformer_front_off");
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
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
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
    public IIcon getIcon(int Side, int Metadata){
    	if(Side == 0){
    		return BottomIcon;
    	} else if(Side == 1){
    		return TopIcon;
    	} else if(Side != Metadata){
    		return SideIcon;
    	}else{
    		if (this.isActive)
    		{
    			return FrontIcon_on;
    		}
    		else
    		{
    			return FrontIcon_off;
    		}
    	}
    }
    
    @Override
    public boolean onBlockActivated(World world,int x,int y,int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		
    	if(player.getHeldItem() != null && FluidContainerRegistry.isEmptyContainer(player.getHeldItem())){
    		if(world.getTileEntity(x, y, z) instanceof TileEntityCarbonFormer){
    			if(((TileEntityCarbonFormer)world.getTileEntity(x, y, z)).tank.getFluidAmount() >= 1000){
    				ItemStack item = FluidContainerRegistry.fillFluidContainer(new FluidStack(PlastiCraft.plastic_fluid, 1000), player.getHeldItem());
    				if(item != null){
    					((TileEntityCarbonFormer)world.getTileEntity(x, y, z)).drain(ForgeDirection.NORTH, 1000, true);
	    				player.inventory.setInventorySlotContents(player.inventory.currentItem, item);
	    				return true;
    				}
    			}
    		}
    		
    	}
    	
    	if(player.isSneaking()){
    		return false;
    	}
    	
    	if(!world.isRemote){
    		FMLNetworkHandler.openGui(player, PlastiCraft.instance, 0, world, x, y, z);
    	}
    	
    	return true;
    	
    }

	@Override
	public void breakBlock(World world, int x,int y,int z, Block block, int metadata){
		if(!keepInventory){
			TileEntity te = world.getTileEntity(x, y, z);
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
		
		super.breakBlock(world, x, y, z, block, metadata);
	}

    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        keepInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, PCBlocks.carbon_former_burning);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, PCBlocks.carbon_former_idle);
        }

        keepInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        if(tileentity != null){
        	tileentity.validate();
        	par1World.setTileEntity(par2, par3, par4, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityCarbonFormer();
	}
}
