package plasticraft.items;

import plasticraft.PlastiCraft;
import plasticraft.blocks.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class Items {

    public static Item plastic_Item;
    public static Item lunchBox;
    public static Item bucketplastic;
    public static ItemFood steak;
    public static Item knife;
    public static Item sliceBread;
	
	public static void Init(Configuration config){
    	lunchBox = new LunchBox(config.getItem("lunch box", 1000).getInt(1000)).setUnlocalizedName("lunchbox");
    	GameRegistry.registerItem(lunchBox, "lunchbox");
    	LanguageRegistry.addName(lunchBox, "Lunch Box");
    	
        bucketplastic = new BucketPlastic(config.getItem("bucketplastic", 602).getInt(602), Blocks.Fluid_Plastic_Block.blockID);
        GameRegistry.registerItem(bucketplastic,"bucketplastic");
        FluidContainerRegistry.registerFluidContainer(PlastiCraft.plastic_fluid, new ItemStack(bucketplastic,1 ,1), new ItemStack(Item.bucketEmpty));
        LanguageRegistry.addName(bucketplastic, "Plastic Bucket");
        
        
        plastic_Item= new Plastic(config.getBlock("Plastic", 501).getInt(501)).setUnlocalizedName("plastic");
        LanguageRegistry.addName(plastic_Item, "Plastic");
        GameRegistry.registerItem(plastic_Item, "plastic");
        plastic_Item.setCreativeTab(PlastiCraft.tabsPC);
        
        steak = new Sandwich(config.getItem("hamburger", 600).getInt(), 10 , false, 10, 5, 1);
        GameRegistry.registerItem(steak, "hamburger");
        LanguageRegistry.addName(steak, "Hamburger sandwich");
        
        knife = new Knife(config.getItem("knife", 603).getInt()).setUnlocalizedName("knife");
        GameRegistry.registerItem(knife, "knife");
        LanguageRegistry.addName(knife, "Knife");
        
        sliceBread = new SliceBread(config.getItem("slicebread", 604).getInt()).setUnlocalizedName("slicebread");
        GameRegistry.registerItem(sliceBread, "slicebread");
        LanguageRegistry.addName(sliceBread, "Bread Slice");
	}
	
}