package plasticraft;

import plasticraft.items.PCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PlastiTab extends CreativeTabs {

	public PlastiTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return PCItems.plastic_Item;
	}

}
