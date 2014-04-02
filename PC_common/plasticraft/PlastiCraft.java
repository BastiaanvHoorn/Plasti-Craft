package plasticraft;

import java.util.logging.Logger;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import network.packetPipeLine;
import plasticraft.blocks.PCBlocks;
import plasticraft.entity.Entities;
import plasticraft.events.bucketevent;
import plasticraft.fluid.PlasticFluid;
import plasticraft.fluid.gasFluid;
import plasticraft.items.PCItems;
import plasticraft.lib.References;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=References.MOD_ID, name=References.MOD_NAME, version=References.VERSION)

public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance(value= References.MOD_ID)
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="plasticraft.client.ClientProxy", serverSide="plasticraft.CommonProxy")
        public static CommonProxy proxy;
        
        public static final Material QuicksandMaterial = new Material(MapColor.dirtColor);
        
        private static Configuration config;
        
        public static int LunchBoxId;
        
        public static Logger pcLog = Logger.getLogger("PlastiCraft");
        
        public static Fluid plastic_fluid;
        
        public static CreativeTabs tabsPC; 
        
        public static Material plastic;
        
        public packetPipeLine pipeLine = new packetPipeLine();
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	config = new Configuration(((FMLPreInitializationEvent) event).getSuggestedConfigurationFile());
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
        	config.load();
        	
        	tabsPC = new CreativeTabs("PlastiTab"){

				@Override
				public Item getTabIconItem() {
					return PCItems.plastic_Item;
				}
        	};
        	
        	pipeLine.initialize();
        	
            plastic = new MaterialLiquid(MapColor.ironColor);
            plastic_fluid = new PlasticFluid("Plastic").setBlock(PCBlocks.Fluid_Plastic_Block);
            
            FluidRegistry.registerFluid(plastic_fluid);
        	plasticraft.blocks.PCBlocks.init();
        	plasticraft.items.PCItems.Init();     
        	
        	References.doStill = config.get("debug", "stillPlasticFluid", true).getBoolean(true);
        			
            MinecraftForge.EVENT_BUS.register(new bucketevent());
            Entities.Init();
            
    		config.save();
    		pcLog.info("PlastiCraft succesfully loaded");
        	proxy.registerRenderers();
        	
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.block_Quicksand,2), "xyx","yzy","xyx",
        	'x', new ItemStack(Blocks.dirt),'y',new ItemStack(Blocks.gravel),'z',new ItemStack(Items.water_bucket)
        		);
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.carbon_former_idle, 1),
        			"xxx","xyx","xxx",
        			'x', new ItemStack(Blocks.nether_brick),
        			'y', new ItemStack(Blocks.furnace));
        	
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.BlockPlastic, 1), "xxx", "xxx", "xxx",
        			'x', new ItemStack(PCItems.plastic_Item));
        	
        	GameRegistry.addShapelessRecipe(new ItemStack(PCItems.plastic_Item, 9), new ItemStack(PCBlocks.BlockPlastic,1));
        	
        }       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	pipeLine.postInitialise();
        }
        
        /*
         * Text of type String.
         */
        public static void info(String text){
        	pcLog.info(text);
        }
        
        /*
         * Text of type int.
         */
        public static void info(int text)
        {
        	pcLog.info(String.valueOf(text));
        }
        
        /*
         * Text of type boolean.
         */
        public static void info(boolean text)
        {
        	pcLog.info(String.valueOf(text));
        }
}