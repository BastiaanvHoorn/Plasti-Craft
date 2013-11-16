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
import plasticraft.blocks.BlockQuicksand;
import plasticraft.blocks.Fluid_Plastic;
import plasticraft.blocks.carbonformer;
import plasticraft.client.interfaces.GuiHandler;
import plasticraft.events.bucketevent;
import plasticraft.fluid.PlasticFluid;
import plasticraft.items.BucketPlastic;
import plasticraft.items.Plastic;
import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
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
@NetworkMod(clientSideRequired=true, serverSideRequired=true)

public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance(value= References.MOD_ID)
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="plasticraft.client.ClientProxy", serverSide="plasticraft.CommonProxy")
        public static CommonProxy proxy;
        

        public static final Material QuicksandMaterial = new Material(MapColor.dirtColor);
        
        
        /*public static Block Quicksand_Still = new BlockQuicksandStill(501, Material.water);
        public static Block Quicksand_Moving = new BlockQuicksandMoving(502, Material.water);
        */

        public static Logger pcLog = Logger.getLogger("PLastiCraft");
        public static Block block_Quicksand;
        public static Block carbon_former_idle;
        public static Block carbon_former_burning;
        public static Item plastic_Item;
        public static Fluid plastic_fluid;
        public static Block Fluid_Plastic_Block;
        public static Item bucketplastic;
        public static final CreativeTabs tabsPC = new CreativeTabs("PlastiTab"){
        	public ItemStack getIconItemStack(){
        		return new ItemStack(block_Quicksand);
        	}
        };
        
        public static Material plastic;
        
        public static int fluidPlasticId;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
    		pcLog.setParent(FMLLog.getLogger());
    		pcLog.info("PlastiCraft preInitialization started");

        	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        	config.load();
        	
        	fluidPlasticId = config.getBlock("plastic_fluid", 600).getInt(600);
            
        	
            block_Quicksand =  new BlockQuicksand(config.getBlock("Quicksand", 500).getInt(500),Material.ground).setUnlocalizedName("quicksand");
            carbon_former_idle = new carbonformer(config.getBlock("carbonformer", 503).getInt(503),Material.iron, 16000, false).setUnlocalizedName("carbonformer_idle");
            carbon_former_burning = new carbonformer(config.getBlock("carbonformer", 503).getInt() + 1, Material.iron, 16000, true).setUnlocalizedName("carbonformer_burning");
            LanguageRegistry.addName(carbon_former_idle, "carbon former");
            GameRegistry.registerBlock(carbon_former_idle,"carbonformer_idle");
            carbon_former_idle.setCreativeTab(tabsPC);
            LanguageRegistry.addName(carbon_former_burning, "carbon former");
            GameRegistry.registerBlock(carbon_former_burning, "carbon_former_burning");
            LanguageRegistry.addName(block_Quicksand, "Quicksand");
            GameRegistry.registerBlock(block_Quicksand,"quicksand");
            block_Quicksand.setCreativeTab(tabsPC);
    
            plastic = new MaterialLiquid(MapColor.ironColor);
            
            plastic_fluid = new PlasticFluid("Plastic").setBlockID(fluidPlasticId);
            
            FluidRegistry.registerFluid(plastic_fluid);
            Fluid_Plastic_Block = new Fluid_Plastic(fluidPlasticId, plastic_fluid, plastic);

            
            GameRegistry.registerBlock(Fluid_Plastic_Block, "plasticBlockfluid");
            LanguageRegistry.addName(Fluid_Plastic_Block, "Plastic");
            
            bucketplastic = new BucketPlastic(config.getItem("bucketplastic", 602).getInt(602), PlastiCraft.Fluid_Plastic_Block.blockID);
            GameRegistry.registerItem(bucketplastic,"bucketplastic");
            FluidContainerRegistry.registerFluidContainer(PlastiCraft.plastic_fluid, new ItemStack(PlastiCraft.bucketplastic,1 ,1), new ItemStack(Item.bucketEmpty));
            LanguageRegistry.addName(bucketplastic, "Plastic Bucket");
            
            
            
            MinecraftForge.EVENT_BUS.register(new bucketevent());
            
            GameRegistry.registerTileEntity(TileEntityCarbonFormer.class, References.CARBONFORMER_TE_KEY); 
            
            new GuiHandler();
            
            plastic_Item= new Plastic(config.getBlock("Plastic", 501).getInt(501)).setUnlocalizedName("plastic");
            LanguageRegistry.addName(plastic_Item, "Plastic");
            GameRegistry.registerItem(plastic_Item, "plastic");
            plastic_Item.setCreativeTab(tabsPC);
    		config.save();
    		LanguageRegistry.instance().addStringLocalization("itemGroup.PlastiTab", "PlastiCraft");
    		pcLog.info("PlastiCraft succesfully loaded");

        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
        	proxy.registerRenderers();
        	
        	GameRegistry.addRecipe(new ItemStack(PlastiCraft.block_Quicksand,2), "xyx","yzy","xyx",
        	'x', new ItemStack(Block.dirt),'y',new ItemStack(Block.gravel),'z',new ItemStack(Item.bucketWater)
        		);
        	GameRegistry.addRecipe(new ItemStack(PlastiCraft.carbon_former_idle,1),
        			"xxx","xyx","xxx",
        			'x', new ItemStack(Block.netherBrick),
        			'y', new ItemStack(Block.furnaceIdle));
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}