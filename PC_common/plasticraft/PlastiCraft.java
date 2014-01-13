package plasticraft;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import network.PacketHandler;
import plasticraft.blocks.Blocks;
import plasticraft.client.interfaces.GuiHandler;
import plasticraft.entity.Entities;
import plasticraft.events.bucketevent;
import plasticraft.fluid.PlasticFluid;
import plasticraft.items.Items;
import plasticraft.lib.References;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=References.MOD_ID, name=References.MOD_NAME, version=References.VERSION)
@NetworkMod(channels = {"pc"}, clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class)

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
        
        public static Logger pcLog = Logger.getLogger("PLastiCraft");
        
        public static Fluid plastic_fluid;
        
        public static CreativeTabs tabsPC; 
        
        public static Material plastic;
        
        public static int fluidPlasticId;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	config = new Configuration(((FMLPreInitializationEvent) event).getSuggestedConfigurationFile());
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
    		pcLog.setParent((Logger) FMLLog.getLogger());
    		pcLog.info("PlastiCraft preInitialization started");
    		
        	config.load();
        	
        	tabsPC = new CreativeTabs("PlastiTab"){

				@Override
				public Item getTabIconItem() {
					return Items.plastic_Item;
				}
        	};
        	
        	fluidPlasticId = config.getBlock("plastic_fluid", 600).getInt(600);
            plastic = new MaterialLiquid(MapColor.ironColor);
            plastic_fluid = new PlasticFluid("Plastic").setBlockID(fluidPlasticId);
            
            FluidRegistry.registerFluid(plastic_fluid);          
        	Blocks.init(config);
        	Items.Init(config);            
            
            MinecraftForge.EVENT_BUS.register(new bucketevent());
            
            new GuiHandler();
            
            Entities.Init();
            
    		config.save();
    		LanguageRegistry.instance().addStringLocalization("itemGroup.PlastiTab", "PlastiCraft");
    		pcLog.info("PlastiCraft succesfully loaded");
        	proxy.registerRenderers();
        	
        	GameRegistry.addRecipe(new ItemStack(Blocks.block_Quicksand,2), "xyx","yzy","xyx",
        	'x', new ItemStack(Block.dirt),'y',new ItemStack(Block.gravel),'z',new ItemStack(Item.bucketWater)
        		);
        	GameRegistry.addRecipe(new ItemStack(Blocks.carbon_former_idle, 1),
        			"xxx","xyx","xxx",
        			'x', new ItemStack(Block.netherBrick),
        			'y', new ItemStack(Block.furnaceIdle));
        	
        	GameRegistry.addRecipe(new ItemStack(Blocks.BlockPlastic, 1), "xxx", "xxx", "xxx",
        			'x', new ItemStack(Items.plastic_Item));
        	
        	GameRegistry.addShapelessRecipe(new ItemStack(Items.plastic_Item, 9), new ItemStack(Blocks.BlockPlastic,1));
        	
        	GameRegistry.addRecipe(new ItemStack(Items.steak, 1), "x", "y", "x", 'x', new ItemStack(Items.sliceBread), 'y', new ItemStack(Item.beefCooked));
        	
        	GameRegistry.addShapedRecipe(new ItemStack(Items.knife, 1), "xyy",
        			'x', new ItemStack(Items.plastic_Item),
        			'y', new ItemStack(Item.ingotIron));
        	
        	for (int i = 0; i < 64; i++)
        	{
        		GameRegistry.addShapelessRecipe(new ItemStack(Items.sliceBread, 4), new Object[]
        			{Item.bread, new ItemStack(Items.knife, 1, i)});	
        	}
        	
        	GameRegistry.addShapedRecipe(new ItemStack(Items.grindFrame), "   ", "x x", "yyy",
        			'x', new ItemStack(Item.flint),
        			'y', new ItemStack(Items.plastic_Item));
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
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