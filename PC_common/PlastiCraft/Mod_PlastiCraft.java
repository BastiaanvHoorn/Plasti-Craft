package PlastiCraft;

import java.util.logging.Logger;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

import PlastiCraft.lib.References;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Mod_PlastiCraft {
	public static Logger pcLog = Logger.getLogger("PLastiCraft");
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		pcLog.setParent(FMLLog.getLogger());
		pcLog.info("PlastiCraft preInitialization started");
		pcLog.info("PlastiCraft succesfully loaded");
		Item itemPlastic = new ItemPLastic(5000,64,CreativeTabs.tabMisc,"Plastic");
		LanguageRegistry.addName(itemPlastic, "Plastic");
		
	}
	public void updateIcons(IconRegister iconRegister)
	{
		
	}
	@Init
	public void init(FMLInitializationEvent event){
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
