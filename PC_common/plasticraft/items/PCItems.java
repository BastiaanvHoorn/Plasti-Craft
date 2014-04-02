package plasticraft.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import plasticraft.PlastiCraft;
import plasticraft.blocks.PCBlocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class PCItems {

    public static Item plastic_Item;
    public static Item bucketplastic;
    public static Item spawnCarbonite;
	
	public static void Init(){
    	
        bucketplastic = new BucketPlastic(PCBlocks.Fluid_Plastic_Block);
        GameRegistry.registerItem(bucketplastic,"bucketplastic");
        FluidContainerRegistry.registerFluidContainer(PlastiCraft.plastic_fluid, new ItemStack(bucketplastic), new ItemStack(Items.bucket));
        
        
        plastic_Item= new Plastic().setUnlocalizedName("plastic");
        GameRegistry.registerItem(plastic_Item, "plastic");
        plastic_Item.setCreativeTab(PlastiCraft.tabsPC);
        
        spawnCarbonite = new SpawnCarbonite();
        GameRegistry.registerItem(spawnCarbonite, "spawncarbonite");
	}
	
}
