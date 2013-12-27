package plasticraft.items;

import plasticraft.PlastiCraft;
import net.minecraft.item.ItemFood;

public class Sandwich extends ItemFood {

	public Sandwich(int id, int healAmount, float saturationModifier, boolean wolf) {
		super(id, healAmount, saturationModifier, wolf);
	}

	public Sandwich(int id, int healAmount, boolean wolf,int potionID,int duration, int amplifier ) {
		super(id, healAmount, wolf);
		setCreativeTab(PlastiCraft.tabsPC);
		setAlwaysEdible();
		setPotionEffect(potionID,duration, amplifier , 1F);
	}

}
