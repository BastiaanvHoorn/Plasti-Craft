package plasticraft.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeGrindStone;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//<<<<<<< 88df0315a75573133b99c25245d068adda6e407c
//=======
public class GrindStone extends BlockContainer {
	
	private boolean isActive;
//>>>>>>> e7c17e2854c16e88c6e1840aa8c6f58bca038a40

	public GrindStone(int id) {
		super(id, Material.anvil);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("GrindStone");
		setTextureName(References.MOD_ID +":grindStone");
		isActive = false;
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
//<<<<<<< 88df0315a75573133b99c25245d068adda6e407c
//=======
	
	@Override
	public TileEntity createNewTileEntity(World world){
		return new TeGrindStone();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int metadata){
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
//>>>>>>> e7c17e2854c16e88c6e1840aa8c6f58bca038a40

}
