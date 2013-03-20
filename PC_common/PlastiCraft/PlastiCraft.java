package plasticraft;


import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidStack;

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

import plasticraft.blocks.block_Quicksand;
@Mod(modid="PlastiCraft", name="PlastiCraft", version="0.0.2")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance("PlastiCraft")
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="plasticraft.client.ClientProxy", serverSide="plasticraft.CommonProxy")
        public static CommonProxy proxy;
                
        
        public static final CreativeTabs tabsPC/*system reference*/ = new CreativeTabs(CreativeTabs.creativeTabArray.length, "PlastiTab"/*ingame name*/);
        public static final Material QuicksandMaterial = new Material(MapColor.dirtColor);
        
        public static Block block_Quicksand = new block_Quicksand(500, 0, PlastiCraft.QuicksandMaterial);/*rest of declarations are in block_quicksand.java*/
        
        
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();                
                
                LanguageRegistry.addName(block_Quicksand, "Quicksand");
                MinecraftForge.setBlockHarvestLevel(block_Quicksand, "shovel", 2);
                GameRegistry.registerBlock(block_Quicksand, "PCQuicksand"/*must be unique*/);
                
                
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