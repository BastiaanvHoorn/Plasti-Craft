package plasticraft.items;

import net.minecraft.item.Item;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class Knife extends Item{

	public Knife(int par1) {
		super(par1);
		setCreativeTab(PlastiCraft.tabsPC);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
		setTextureName(References.MOD_ID.toLowerCase()+ ":knife");
	}
}
