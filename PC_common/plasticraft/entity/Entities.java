package plasticraft.entity;

import plasticraft.PlastiCraft;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {

	public static void Init(){
		EntityRegistry.registerModEntity(EntityCarbonite.class, "EntityCarbonite", 0, PlastiCraft.instance, 80, 3, true);
	}
	
}
