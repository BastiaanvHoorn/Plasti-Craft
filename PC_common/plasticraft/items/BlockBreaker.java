package plasticraft.items;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BlockBreaker extends Item {
	public BlockBreaker(){
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("blockbreaker");
		setTextureName(References.MOD_ID.toLowerCase() + ":blockbreaker");
		setMaxStackSize(1);
	}
	
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
