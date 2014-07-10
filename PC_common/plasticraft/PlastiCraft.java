package plasticraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

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
import plasticraft.items.PCItems;
import plasticraft.lib.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=References.MOD_ID, name=References.MOD_NAME, version=References.VERSION)//Declares some mod vaues

public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance(value= References.MOD_ID)
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="plasticraft.client.ClientProxy", serverSide="plasticraft.CommonProxy")
        public static CommonProxy proxy;
        //The configuration object for the config file
        private static Configuration config;
        //The logger object for the logger
        public static org.apache.logging.log4j.Logger pcLog = LogManager.getLogger("PlastiCraft");
        //The object used to fill fluid containers and such
        public static Fluid plastic_fluid;
        //The vreative tab object
        public static CreativeTabs tabsPC = new PlastiTab(CreativeTabs.getNextID(), "PlastiCraft"); 
        //The material for the plastic block
        public static Material plastic;
        //The object used for sending packets
        public static packetPipeLine pipeLine = new packetPipeLine();
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {//Gets called in the preInitialization fase
        	//Declaring the configuration file object
        	config = new Configuration(((FMLPreInitializationEvent) event).getSuggestedConfigurationFile());
        	config.load();//Loads the config file variables from the file
        	//Declares the material used for the plastic fluid block
            plastic = new MaterialLiquid(MapColor.ironColor);
            //declare the plastic fluid source block(and still ones)
            plastic_fluid = new PlasticFluid("Plastic").setBlock(PCBlocks.Fluid_Plastic_Block);
            //register the plastic fluid
            FluidRegistry.registerFluid(plastic_fluid);
            //register all plasticraft blocks
        	PCBlocks.init();
        	//register all plasticraft items
        	PCItems.Init();     
        	//get a config value and store it in the references java file
        	References.doStill = config.get("debug", "stillPlasticFluid", true).getBoolean(true);
        	//register the event to fill a bucket with liquid plastic		
            MinecraftForge.EVENT_BUS.register(new bucketevent());
            //register all plasticraft entities
            Entities.Init();
            //save the config(you can't make changes after this)
    		config.save();
    		//logging to the console that plasticraft succesfully loaded
    		pcLog.info("PlastiCraft succesfully loaded");
    		//registers the renders(only on the client)
        	proxy.registerRenderers();
        	
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {//gets called in the intialization fase
        	//intialize the pipeline
        	pipeLine.initialize();
        	//register a recipe
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.block_Quicksand,2), "xyx","yzy","xyx",
        	'x', new ItemStack(Blocks.dirt),'y',new ItemStack(Blocks.gravel),'z',new ItemStack(Items.water_bucket)
        		);
        	//register another recipe
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.carbon_former_idle, 1),
        			"xxx","xyx","xxx",
        			'x', new ItemStack(Blocks.nether_brick),
        			'y', new ItemStack(Blocks.furnace));
        	//register another recipe
        	GameRegistry.addRecipe(new ItemStack(PCBlocks.BlockPlastic, 1), "xxx", "xxx", "xxx",
        			'x', new ItemStack(PCItems.plastic_Item));
        	//register another recipe
        	GameRegistry.addShapelessRecipe(new ItemStack(PCItems.plastic_Item, 9), new ItemStack(PCBlocks.BlockPlastic,1));
        }       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {//gets callled in the postInitialization fase
        	//post initaialize the pipeline
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
        /*
         *Text of type double 
         */
		public static void info(double text) {
			pcLog.info(String.valueOf(text));
		}
}