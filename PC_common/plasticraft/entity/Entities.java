package plasticraft.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import plasticraft.PlastiCraft;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {

	public static void Init(){
		EntityRegistry.registerModEntity(EntityCarbonite.class, "EntityCarbonite", 0, PlastiCraft.instance, 80, 1, false);
		EntityRegistry.addSpawn(EntityCarbonite.class, 1, 0, 1, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills);
	
		EntityRegistry.registerModEntity(EntityClone.class, "EntityClone", 1, PlastiCraft.instance, 80, 1, false);
	}
	
}
