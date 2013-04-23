package PlastiCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPLastic extends Item {

	public ItemPLastic(int id,int maxStack,CreativeTabs tab,String name) {
		super(id);
		setMaxStackSize(maxStack);
		
		setCreativeTab(tab);
		
		setUnlocalizedName(name);
	}
	
}
