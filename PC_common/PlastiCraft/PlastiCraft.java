package PlastiCraft;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import PlastiCraft.blocks.BlockQuicksand;

@Mod(modid="PlastiCraft", name="plasticraft", version="0.0.2")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance("PlastiCraft")
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="PlastiCraft.client.ClientProxy", serverSide="PlastiCraft.CommonProxy")
        public static CommonProxy proxy;
        
        public static int BlockQuicksandID;
        
        public static final CreativeTabs tabsPC = new CreativeTabs(CreativeTabs.creativeTabArray.length, "PlastiTab");
        public static final Material QuicksandMaterial = new Material(MapColor.dirtColor);
        
        public static Block block_Quicksand = new BlockQuicksand(BlockQuicksandID, PlastiCraft.QuicksandMaterial);
        /*public static Block Quicksand_Still = new BlockQuicksandStill(501, Material.water);
        public static Block Quicksand_Moving = new BlockQuicksandMoving(502, Material.water);
        */
        public static Logger pcLog = Logger.getLogger("PLastiCraft");
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
    		pcLog.setParent(FMLLog.getLogger());
    		pcLog.info("PlastiCraft preInitialization started");

        	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        	config.load();
        	
            BlockQuicksandID = config.getBlock("BlockQuicksand", 500).getInt();
        	
        	config.save();
    		pcLog.info("PlastiCraft succesfully loaded");
        	
        }
        
        @Init
        public void load(FMLInitializationEvent event) {    
                
                
                LanguageRegistry.addName(block_Quicksand, "Quicksand");
                MinecraftForge.setBlockHarvestLevel(block_Quicksand, "shovel", 2);
                GameRegistry.registerBlock(block_Quicksand, "PCQuicksand");
                
                
                ItemStack StackSoulsand = new ItemStack(Block.slowSand);
                GameRegistry.addShapelessRecipe(new ItemStack(PlastiCraft.block_Quicksand, 2),
                	new ItemStack(Block.dirt),
                    new ItemStack(Block.sand),
                    new ItemStack(Block.gravel),
                    StackSoulsand, StackSoulsand, StackSoulsand
                );
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}