package PlastiCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(modid="PlastiCraft", name="PlastiCraft", version="0.0.2")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class PlastiCraft {

        // The instance of your mod that Forge uses.
        @Instance("PlastiCraft")
        public static PlastiCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="PlastiCraft.client.ClientProxy", serverSide="PlastiCraft.CommonProxy")
        public static CommonProxy proxy;
        
        public static final CreativeTabs tabsPC/*system reference*/ = new CreativeTabs(CreativeTabs.creativeTabArray.length, "PlastiTab"/*ingame name*/);
        public static final Material QuicksandMaterial = new Material(MapColor.dirtColor);
        public final static Block Block_Quicksand = new Block_Quicksand(500, 1, Material.ground)
        	.setHardness(0.5F).setStepSound(Block.soundGravelFootstep)
        	.setBlockName("Quicksand1");
        
        
        
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();                
                
                LanguageRegistry.addName(Block_Quicksand, "Quicksand"/*ingame name*/);
                MinecraftForge.setBlockHarvestLevel(Block_Quicksand, "shovel", 2);
                GameRegistry.registerBlock(Block_Quicksand, "PCQuicksand"/*must be unique*/);
        
                GameRegistry.addShapelessRecipe(new ItemStack(Block.beacon, 64),
                	new ItemStack(Block.dirt),
                    new ItemStack(Block.sand),
                    new ItemStack(Block.gravel),
                    new ItemStack(Block.slowSand, 3));
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}