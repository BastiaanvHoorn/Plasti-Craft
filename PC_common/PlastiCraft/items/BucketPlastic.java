package plasticraft.items;

import net.minecraft.item.ItemBucket;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class BucketPlastic extends ItemBucket{

	public BucketPlastic(int id, int par2) {
		super(id, par2);
		setUnlocalizedName("bucketplastic");
		setCreativeTab(PlastiCraft.tabsPC);
		setTextureName(References.MOD_ID.toLowerCase()+ ":bucketPlastic");
	}
	
	

}
