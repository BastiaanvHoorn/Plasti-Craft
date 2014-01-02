package plasticraft.items;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import net.minecraft.item.Item;

public class GrindFrame extends Item{

	public GrindFrame(int par1) {
		super(par1);
		this.setMaxDamage(1024);
		this.setMaxStackSize(1);
		this.setCreativeTab(PlastiCraft.tabsPC);
		this.setTextureName(References.MOD_ID.toLowerCase() + ":grindFrame");
	}
}
