package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeFluidPlastic;
import plasticraft.tileentities.TeGrindStone;
import plasticraft.tileentities.TeTrashCan;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PCBlocks {
	
	 public static Block block_Quicksand;
     public static Block carbon_former_idle;
     public static Block carbon_former_burning;
     public static Block BlockPlastic;
     public static Block Fluid_Plastic_Block;
     public static Block grindStone_idle;
     public static Block grindStone_grinding;
     public static Block trashCan; 
     public static Block fluidGasBlock;
     
	public static void init(){
		
        block_Quicksand =  new BlockQuicksand(Material.ground);
        carbon_former_idle = new carbonformer(Material.iron,false);
        carbon_former_burning = new carbonformer(Material.iron,true);
        GameRegistry.registerBlock(carbon_former_idle,"carbonformer_idle");
        carbon_former_idle.setCreativeTab(PlastiCraft.tabsPC);
        GameRegistry.registerTileEntity(TileEntityCarbonFormer.class, References.CARBONFORMER_TE_KEY);
        GameRegistry.registerTileEntity(TeFluidPlastic.class, References.FLUIDPLASTIC_TE_KEY);
        GameRegistry.registerTileEntity(TeTrashCan.class, References.TRASHCAN_TE_KEY);
        GameRegistry.registerBlock(carbon_former_burning, "carbon_former_burning");
        GameRegistry.registerBlock(block_Quicksand,"quicksand");
        block_Quicksand.setCreativeTab(PlastiCraft.tabsPC);
        
        BlockPlastic = new BlockPlastic();
        GameRegistry.registerBlock(BlockPlastic, "BlockPlastic");
        
        Fluid_Plastic_Block = new Fluid_Plastic(PlastiCraft.plastic_fluid, PlastiCraft.plastic);
        GameRegistry.registerBlock(Fluid_Plastic_Block, "plasticBlockfluid");
        
        grindStone_idle = new GrindStone(false);
        grindStone_grinding = new GrindStone(true);
        GameRegistry.registerBlock(grindStone_idle, "grindStone_idle");
        GameRegistry.registerBlock(grindStone_grinding, "grindStone_grinding");
        GameRegistry.registerTileEntity(TeGrindStone.class, References.GRINDSTONE_TE_KEY);
		grindStone_idle.setCreativeTab(PlastiCraft.tabsPC);
		
		trashCan = new BlockTrashCan(Material.iron);
		GameRegistry.registerBlock(trashCan, "trashcan");
		
		fluidGasBlock = new FluidGas(PlastiCraft.gasFluid, PlastiCraft.plastic);
		GameRegistry.registerBlock(fluidGasBlock, "fluidGas");
	}
	
	
}
