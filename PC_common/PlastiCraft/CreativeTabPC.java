package PlastiCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

final class CreativeTabPC extends CreativeTabs{
	
	public static final CreativeTabs tabPlastiCraft=new CreativeTabPC("PlastiCraft");

	public CreativeTabPC(String label){
		super(label);
	}
	

	@Override
    public ItemStack getIconItemStack() {
		return new ItemStack(Item.appleGold);
	}
	
	@Override
    public String getTranslatedTabLabel() {
		return "PlastiCraft";
	}
}
