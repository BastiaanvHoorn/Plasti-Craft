package plasticraft.blocks;

import java.util.Random;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeGrindStone;
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
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GrindStone extends BlockContainer {
	
	private boolean isActive;

	public GrindStone(int id, boolean isActive)
	{
		super(id, Material.anvil);
		this.setHardness(4F);
		this.isActive = isActive;
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int Side, float hitX,float hitY, float hitZ){
		if(player.isSneaking())
		{
    		return false;
    	}
    	
    	if(!world.isRemote)
    	{
    		FMLNetworkHandler.openGui(player, PlastiCraft.instance, 2, world, x, y, z);
    	}
    	
    	return true;
    	
    }
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TeGrindStone();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int metadata)
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(te != null && te instanceof IInventory){
			TeGrindStone inv = (TeGrindStone)te;
			
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
	
	@SideOnly(Side.CLIENT)
    public static Icon TopIcon;
    @SideOnly(Side.CLIENT)
    public static Icon SideIcon;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
    	TopIcon = this.isActive ? icon.registerIcon(References.MOD_ID.toLowerCase() + ":grindStone_top_on") : icon.registerIcon(References.MOD_ID.toLowerCase() +":grindStone_top_off");
    	SideIcon = icon.registerIcon(References.MOD_ID.toLowerCase() + ":grindStone_side");
    	
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int Side, int Metadata)
    {
    	if (Side == 1)
    	{
    		return TopIcon;
    	}
    	else
    	{
    		return SideIcon;
    	}
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
    }
    
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	if (this.isActive)
    	{
	    	double random1 = 0;
	    	
	    	if (0.5F - par5Random.nextDouble() > 0)
	    	{
	    		random1 = 1D;
	    	}
	    	else
	    	{
	    		random1 = -1D;
	    	}
	    	
	    	double random2 = 0;
	    	
	    	if (0.5F - par5Random.nextDouble() > 0)
	    	{
	    		random2 = 1D;
	    	}
	    	else
	    	{
	    		random2 = -1D;
	    	}
	    	
	    	par1World.spawnParticle("crit", (double)par2 + par5Random.nextDouble(), (double)par3 + 0.9D, (double)par4 + par5Random.nextDouble(), 0.2D * random1, 0.4D, 0.2D * random2);
    	}
    }
    
    public static void updateBlockState(boolean state, World world, int x, int y, int z)
    {
    	int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);

        if (state)
        {
            world.setBlock(x, y, z, Blocks.grindStone_grinding.blockID);
        }
        else
        {
            world.setBlock(x, y, z, Blocks.grindStone_idle.blockID);
        }

        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(x, y, z, tileentity);
        }
    }
}
