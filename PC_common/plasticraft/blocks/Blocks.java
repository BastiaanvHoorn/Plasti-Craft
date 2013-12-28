package plasticraft.blocks;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeFluidPlastic;
import plasticraft.tileentities.TeLunchBox;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;

public class Blocks {
	
	 public static Block block_Quicksand;
     public static Block carbon_former_idle;
     public static Block carbon_former_burning;
     public static Block BlockPlastic;
     public static Block Fluid_Plastic_Block;
     public static Block grindStone;
     
	public static void init(Configuration config){
		
        block_Quicksand =  new BlockQuicksand(config.getBlock("Quicksand", 500).getInt(500),Material.ground).setUnlocalizedName("quicksand");
        carbon_former_idle = new carbonformer(config.getBlock("carbonformer", 503).getInt(503),Material.iron, 16000, false).setUnlocalizedName("carbonformer_idle");
        carbon_former_burning = new carbonformer(config.getBlock("carbonformer", 503).getInt() + 1, Material.iron, 16000, true).setUnlocalizedName("carbonformer_burning");
        LanguageRegistry.addName(carbon_former_idle, "carbon former");
        GameRegistry.registerBlock(carbon_former_idle,"carbonformer_idle");
        carbon_former_idle.setCreativeTab(PlastiCraft.tabsPC);
        GameRegistry.registerTileEntity(TileEntityCarbonFormer.class, References.CARBONFORMER_TE_KEY);
        GameRegistry.registerTileEntity(TeFluidPlastic.class, References.FLUIDPLASTIC_TE_KEY);
    	GameRegistry.registerTileEntity(TeLunchBox.class, References.LUNCHBOX_TE_KEY);
        LanguageRegistry.addName(carbon_former_burning, "carbon former");
        GameRegistry.registerBlock(carbon_former_burning, "carbon_former_burning");
        LanguageRegistry.addName(block_Quicksand, "Quicksand");
        GameRegistry.registerBlock(block_Quicksand,"quicksand");
        block_Quicksand.setCreativeTab(PlastiCraft.tabsPC);
        
        BlockPlastic = new BlockPlastic(config.getBlock("PlasticBlock", 505).getInt());
        GameRegistry.registerBlock(BlockPlastic, "BlockPlastic");
        LanguageRegistry.addName(BlockPlastic, "Plastic Block");
        
        Fluid_Plastic_Block = new Fluid_Plastic(PlastiCraft.fluidPlasticId, PlastiCraft.plastic_fluid, PlastiCraft.plastic);
        GameRegistry.registerBlock(Fluid_Plastic_Block, "plasticBlockfluid");
        LanguageRegistry.addName(Fluid_Plastic_Block, "Plastic");
        
        grindStone = new GrindStone(700);
        GameRegistry.registerBlock(Blocks.grindStone, "grindstone");
        LanguageRegistry.addName(Blocks.grindStone, "Grindstone");
	}
	
	
}
