package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeFluidPlastic;
import plasticraft.tileentities.TeTrashCan;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.registry.GameRegistry;

public class PCBlocks {
	
	 public static Block block_Quicksand;
     public static Block carbon_former_idle;
     public static Block carbon_former_burning;
     public static Block BlockPlastic;
     public static Block Fluid_Plastic_Block;
     public static Block trashCan; 
     
	public static void init(){
		
        block_Quicksand =  new BlockQuicksand(Material.ground).setBlockName("Quicksand");
        GameRegistry.registerBlock(block_Quicksand,"quicksand");
        block_Quicksand.setCreativeTab(PlastiCraft.tabsPC);
        
        carbon_former_idle = new carbonformer(Material.iron,false).setBlockName("CarbonFormer");
        carbon_former_burning = new carbonformer(Material.iron,true).setBlockName("CarbonFormerBurning");
        GameRegistry.registerBlock(carbon_former_idle,"carbonformer_idle");
        GameRegistry.registerBlock(carbon_former_burning, "carbon_former_burning");
        carbon_former_idle.setCreativeTab(PlastiCraft.tabsPC);
        GameRegistry.registerTileEntity(TileEntityCarbonFormer.class, References.CARBONFORMER_TE_KEY);
        
        BlockPlastic = new BlockPlastic().setBlockName("BlockPlastic");
        GameRegistry.registerBlock(BlockPlastic, "BlockPlastic");
        
        Fluid_Plastic_Block = new Fluid_Plastic(PlastiCraft.plastic_fluid, PlastiCraft.plastic).setBlockName("FluidPlastic");
        GameRegistry.registerBlock(Fluid_Plastic_Block, "plasticBlockfluid");
        GameRegistry.registerTileEntity(TeFluidPlastic.class, References.FLUIDPLASTIC_TE_KEY);
		
		trashCan = new BlockTrashCan(Material.iron).setBlockName("TrashCan");
		GameRegistry.registerBlock(trashCan, "trashcan");
		GameRegistry.registerTileEntity(TeTrashCan.class, References.TRASHCAN_TE_KEY);
		
	}
	
	
}
