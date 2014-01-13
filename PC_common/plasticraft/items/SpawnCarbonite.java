package plasticraft.items;

import javax.swing.text.html.parser.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plasticraft.PlastiCraft;
import plasticraft.entity.EntityCarbonite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnCarbonite extends Item{

	public SpawnCarbonite(int par1) {
		super(par1);
		this.setCreativeTab(PlastiCraft.tabsPC);
		this.setUnlocalizedName("Spawn Carbonite");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		PlastiCraft.info("onitemusefirst");
		if (!world.isRemote)
		{
			PlastiCraft.info("isremote");
			
			EntityCarbonite entity = new EntityCarbonite(world);
			entity.posX = x;
			entity.posY = y + 1.0F;
			entity.posZ = z;
			
			world.spawnEntityInWorld(entity);
			return true;
		}
		
		return false;
	}
}
