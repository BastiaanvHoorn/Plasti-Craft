package plasticraft;

import plasticraft.items.PCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
//this is the class used for the creative tab
public class PlastiTab extends CreativeTabs {
	//see PlastiCraft.java for info
	public PlastiTab(int par1, String par2Str) {
		super(par1, par2Str);
	}
	//gets an Item to render on the tab
	@Override
	public Item getTabIconItem() {
		return PCItems.plastic_Item;
	}

}
