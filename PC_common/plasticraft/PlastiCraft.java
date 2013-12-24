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
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import plasticraft.blocks.Blocks;
import plasticraft.blocks.Fluid_Plastic;
import plasticraft.client.interfaces.GuiHandler;
import plasticraft.events.bucketevent;
import plasticraft.fluid.PlasticFluid;
import plasticraft.items.BucketPlastic;
import plasticraft.items.LunchBox;
import plasticraft.items.Plastic;
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
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

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
       
        public static Item plastic_Item;
        public static Item lunchBox;
        public static Fluid plastic_fluid;

        public static Item bucketplastic;
        public static CreativeTabs tabsPC; 
        
        public static Material plastic;
        
        public static int fluidPlasticId;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	config = new Configuration(((FMLPreInitializationEvent) event).getSuggestedConfigurationFile());

        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
    		pcLog.setParent(FMLLog.getLogger());
    		pcLog.info("PlastiCraft preInitialization started");


        	config.load();

        	tabsPC = new CreativeTabs("PlastiTab"){
            	public ItemStack getIconItemStack(){
            		return new ItemStack(Blocks.block_Quicksand);
            	}
        	};

        	fluidPlasticId = config.getBlock("plastic_fluid", 600).getInt(600);
        	
        	Blocks.init(config);
        	
        	LunchBoxId = config.getItem("lunch box", 1000).getInt(1000);
        	lunchBox = new LunchBox(LunchBoxId).setUnlocalizedName("lunchbox");
        	GameRegistry.registerItem(lunchBox, "lunchbox");
        	LanguageRegistry.addName(lunchBox, "Lunch Box");





            

    
            plastic = new MaterialLiquid(MapColor.ironColor);
            
            plastic_fluid = new PlasticFluid("Plastic").setBlockID(fluidPlasticId);
            
            FluidRegistry.registerFluid(plastic_fluid);
            Blocks.Fluid_Plastic_Block = new Fluid_Plastic(fluidPlasticId, plastic_fluid, plastic);

            
            GameRegistry.registerBlock(Blocks.Fluid_Plastic_Block, "plasticBlockfluid");
            LanguageRegistry.addName(Blocks.Fluid_Plastic_Block, "Plastic");
            
            bucketplastic = new BucketPlastic(config.getItem("bucketplastic", 602).getInt(602), Blocks.Fluid_Plastic_Block.blockID);
            GameRegistry.registerItem(bucketplastic,"bucketplastic");
            FluidContainerRegistry.registerFluidContainer(PlastiCraft.plastic_fluid, new ItemStack(PlastiCraft.bucketplastic,1 ,1), new ItemStack(Item.bucketEmpty));
            LanguageRegistry.addName(bucketplastic, "Plastic Bucket");
            
            
            
            MinecraftForge.EVENT_BUS.register(new bucketevent());
            

            
            new GuiHandler();
            
            plastic_Item= new Plastic(config.getBlock("Plastic", 501).getInt(501)).setUnlocalizedName("plastic");
            LanguageRegistry.addName(plastic_Item, "Plastic");
            GameRegistry.registerItem(plastic_Item, "plastic");
            plastic_Item.setCreativeTab(tabsPC);
    		config.save();
    		LanguageRegistry.instance().addStringLocalization("itemGroup.PlastiTab", "PlastiCraft");
    		pcLog.info("PlastiCraft succesfully loaded");
        	proxy.registerRenderers();
        	
        	GameRegistry.addRecipe(new ItemStack(Blocks.block_Quicksand,2), "xyx","yzy","xyx",
        	'x', new ItemStack(Block.dirt),'y',new ItemStack(Block.gravel),'z',new ItemStack(Item.bucketWater)
        		);
        	GameRegistry.addRecipe(new ItemStack(Blocks.carbon_former_idle,1),
        			"xxx","xyx","xxx",
        			'x', new ItemStack(Block.netherBrick),
        			'y', new ItemStack(Block.furnaceIdle));
        	
        	GameRegistry.addRecipe(new ItemStack(Blocks.BlockPlastic, 1), "xxx", "xxx", "xxx",
        			'x', new ItemStack(PlastiCraft.plastic_Item));
        	
        	GameRegistry.addShapelessRecipe(new ItemStack(PlastiCraft.plastic_Item, 9), new ItemStack(Blocks.BlockPlastic,1));
        }
        
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
        
        public static void info(String text){
        	pcLog.info(text);
        }
}