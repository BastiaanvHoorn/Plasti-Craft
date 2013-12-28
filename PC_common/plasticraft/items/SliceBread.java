package plasticraft.items;

import net.minecraft.item.Item;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class SliceBread extends Item{

	public SliceBread(int par1) {
		super(par1);
		setCreativeTab(PlastiCraft.tabsPC);
		setTextureName(References.MOD_ID.toLowerCase()+ ":sliceBread");
	}
}
