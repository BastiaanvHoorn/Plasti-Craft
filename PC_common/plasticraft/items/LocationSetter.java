package plasticraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class LocationSetter extends Item {

	public LocationSetter() {
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("locationsetter");
		setTextureName(References.MOD_ID.toLowerCase() + ":locationsetter");
		setMaxStackSize(1);
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side	, float par8, float par9, float par10)
    {
		NBTTagCompound compound = stack.stackTagCompound;
		if(compound == null){
			compound = new NBTTagCompound();
		}
		compound.setInteger("x", x);
		compound.setInteger("y", y);
		compound.setInteger("z", z);
		stack.setTagCompound(compound);
		return false;
    }

}
