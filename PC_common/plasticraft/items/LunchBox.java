package plasticraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class LunchBox extends Item{

	public LunchBox(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(PlastiCraft.tabsPC);
	}
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(player.isSneaking()){
			if(!world.isRemote){
				FMLNetworkHandler.openGui(player, PlastiCraft.instance, 1, world, (int)player.posX, (int)player.posY, (int)player.posZ);
				PlastiCraft.info("opening GUI");
			}
		}
		return stack;
    }

}
