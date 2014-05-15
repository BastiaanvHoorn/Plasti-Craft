package plasticraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.entity.EntityCarbonite;
import plasticraft.lib.References;

public class SpawnCarbonite extends Item{

	public SpawnCarbonite() {
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
			
			float posX = x + 0.5F;
			float posY = y;
			float posZ = z + 0.5F;
			
			if (side == 0)
			{
				posY -= 2.0F;
			}
			else if (side == 1)
			{
				posY += 1.0F;
			}
			else if (side == 2)
			{
				posZ -= 1.0F;
			}
			else if (side == 3)
			{
				posZ += 1.0F;
			}
			else if (side == 4)
			{
				posX -= 1.0F;
			}
			else if (side == 5)
			{
				posX += 1.0F;
			}
			entity.setLocationAndAngles(posX, posY, posZ, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;
			entity.onSpawnWithEgg(null);
			world.spawnEntityInWorld(entity);
			return true;
		}
		
		return false;
	}
}
