package net.XDMods.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="XDMods", name="XD Mods", version="0.0.1")
@NetworkMod(clientSideRequired=true,serverSideRequired=true)
public class mod_XDMods {

	//blocks
	public static Block quiksand;

	@PreInit
	public void intiConfig(FMLPreInitializationEvent pie){

	}
	@Init
	public void load(FMLInitializationEvent ie){

		quiksand = (new modBlock(500,0,Material.ground)).setHardness(0.8F).setStepSound(Block.soundGravelFootstep).setBlockName("Quicksand");
		RegistringBlocks();
		blockNames();
		itemNames();
		recipes();
		smelting();

		//MINECRAFT FORGE TEXTURE FUNCTIONS
		MinecraftForgeClient.preloadTexture("XDTexture/terrain.png");
	}

	public void RegistringBlocks(){
		//BLOCKS
		GameRegistry.registerBlock(quiksand);
	}

	public void blockNames(){
		LanguageRegistry.addName(quiksand, "Quicksand");
	}

	public void itemNames(){
		//items
	}

	public void recipes(){
		//recipes
		/*GameRegistry.addRecipe(new ItemStack(quiksand,1), new Object[]{
			"X*X","*B*","X*X",'X',Block.dirt.blockID,'*',Block.sand.blockID,'B',Block.gravel.blockID

		});*/
	}

	public void smelting(){
		//smelting
	}
}
