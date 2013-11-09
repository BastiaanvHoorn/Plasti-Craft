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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class carbonformer extends BlockContainer implements IFluidTank{

	private FluidStack fluid;
	private int amount;
	private int capacity;
	private TileEntity te;
	
	public carbonformer(int id, Material Material) {
		super(id, Material);
		setStepSound(soundStoneFootstep);
		setHardness(2F);
		setLightValue(0.1F);
		this.fluid = null;
		this.amount = 0;
		this.capacity = 16000;
		this.te = new TileEntityCarbonFormer();
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
    
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        if (fluid != null)
        {
            fluid.writeToNBT(nbt);
        }
        else
        {
            nbt.setString("Empty", "");
        }
        return nbt;
    }
    
    public carbonformer readFromNBT(NBTTagCompound nbt)
    {
        if (!nbt.hasKey("Empty"))
        {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

            if (fluid != null)
            {
                setFluid(fluid);
            }
        }
        return this;
    }
    
    private void setFluid(FluidStack fluid2) {
    	this.fluid = fluid2;
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
		
		super.breakBlock(world, x, y, z, id, metadata);
	}

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		return amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
        if (resource == null){
            return 0;
        }
        if(resource.getFluid() == PlastiCraft.plastic_fluid){
	        if (!doFill){
	        	if (fluid == null)
	        	{
	        		return Math.min(capacity, resource.amount);
	            }
	
	        	if (!fluid.isFluidEqual(resource))
	        	{
	        		return 0;
	        	}
	        	
	        	return Math.min(capacity - fluid.amount, resource.amount);
	           }
	        if (fluid == null)
	        {
	            fluid = new FluidStack(resource, Math.min(capacity, resource.amount));
	
	            if (te != null)
	            {
	                FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, te.worldObj, te.xCoord, te.yCoord, te.zCoord, this));
	            }
	            return fluid.amount;
	        }
	        if (!fluid.isFluidEqual(resource))
	        {
	            return 0;
	        }
	        int filled = capacity - fluid.amount;
	
	        if (resource.amount < filled)
	        {
	            fluid.amount += resource.amount;
	            filled = resource.amount;
	        }
	        else
	        {
	            fluid.amount = capacity;
	        }
	
	        if (te != null)
	        {
	            FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, te.worldObj, te.xCoord, te.yCoord, te.zCoord, this));
	        }
	        return filled;
        }
        return 0;
        
        
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		 if (fluid == null)
	        {
	            return null;
	        }
	        int drained = maxDrain;
	        if (fluid.amount < drained)
	        {
	        	drained = fluid.amount;
	        }
	        FluidStack stack = new FluidStack(fluid, drained);
	        if (doDrain)
	        {
	            fluid.amount -= drained;
	            if (fluid.amount <= 0)
	            {
	                fluid = null;
	            }

	            if (te != null)
	            {
	                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, te.worldObj, te.xCoord, te.yCoord, te.zCoord, this));
	            }
	        }
	        return stack;
	}

}
