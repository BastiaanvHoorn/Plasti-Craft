package plasticraft.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class BucketPlastic extends ItemBucket{

	public BucketPlastic(Block par2) {
		super(par2);
		setUnlocalizedName("bucketplastic");
		setCreativeTab(PlastiCraft.tabsPC);
		setTextureName(References.MOD_ID.toLowerCase()+ ":bucketPlastic");
	}
	
	

}
