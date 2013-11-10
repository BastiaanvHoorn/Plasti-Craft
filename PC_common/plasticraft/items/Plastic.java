package plasticraft.items;

import plasticraft.lib.References;
import net.minecraft.item.Item;

public class Plastic extends Item {

	public Plastic(int par1) {
		super(par1);
		setTextureName(References.MOD_ID.toLowerCase() + ":plastic");
	}

}
