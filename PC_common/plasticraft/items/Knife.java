package plasticraft.items;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import plasticraft.items.PCItems;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class Knife extends Item{

	public Knife(int par1) {
		super(par1);
		setCreativeTab(PlastiCraft.tabsPC);
		setTextureName(References.MOD_ID.toLowerCase() + ":knife");
		setMaxStackSize(1);
		setNoRepair();
		setMaxDamage(64);
		isDamageable();
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
		int damage = itemStack.getItemDamage() + 1;
		itemStack.setItemDamage(damage);
		
		/*for (int i = 0; i < 5; i++){
			if (damage >= itemStack.getMaxDamage() / 5 * i && damage < itemStack.getMaxDamage() / 5 * i)
			{
				HashMap map = new HashMap();
				EnchantmentDamage enchantment = new EnchantmentDamage(16, 0, 1);
				map.put(Integer.valueOf(16), new EnchantmentData(enchantment, 0));
				EnchantmentHelper.setEnchantments(map, itemStack);
			}
		}*/                                                                                       
        return itemStack;
    }
}
