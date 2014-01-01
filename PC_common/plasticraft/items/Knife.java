package plasticraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import plasticraft.items.Items;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class Knife extends Item{

	public Knife(int par1) {
		super(par1);
		setCreativeTab(PlastiCraft.tabsPC);
		this.maxStackSize = 1;
		this.setNoRepair();
		this.setMaxDamage(64);
		this.isDamageable();
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
	{
		return false;
	}
	
	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
		ItemStack itemStackDamaged = itemStack.copy();
		itemStackDamaged.setItemDamage(itemStack.getItemDamage() + 1);
        return itemStackDamaged;
    }
}
