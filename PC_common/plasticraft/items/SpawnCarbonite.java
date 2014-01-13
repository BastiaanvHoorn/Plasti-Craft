package plasticraft.items;

import javax.swing.text.html.parser.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plasticraft.PlastiCraft;
import plasticraft.entity.EntityCarbonite;
import plasticraft.lib.References;
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
		setTextureName(References.MOD_ID.toLowerCase() + ":spawnCarbonite");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			EntityCarbonite entity = new EntityCarbonite(world);
			PlastiCraft.info(side);
			
			entity.posX = x + 0.5F;
			entity.posY = y;
			entity.posZ = z + 0.5F;
			
			if (side == 0)
			{
				entity.posY -= 2.0F;
			}
			else if (side == 1)
			{
				entity.posY += 1.0F;
			}
			else if (side == 2)
			{
				entity.posZ -= 1.0F;
			}
			else if (side == 3)
			{
				entity.posZ += 1.0F;
			}
			else if (side == 4)
			{
				entity.posX -= 1.0F;
			}
			else if (side == 5)
			{
				entity.posX += 1.0F;
			}
			world.spawnEntityInWorld(entity);
			return true;
		}
		
		return false;
	}
}
