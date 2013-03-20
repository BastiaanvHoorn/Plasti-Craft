package plasticraft.blocks;


import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraftforge.liquids.ILiquid;

import plasticraft.PlastiCraft;
import plasticraft.CommonProxy;

public class Quicksand_Still extends BlockStationary{

	public Quicksand_Still(int i, Material material){
		super(i, material);
		
		setHardness(100F);
	}
	
	@Override
	public String getTextureFile(){
		return CommonProxy.TEXTURES_PNG;
	}
	
	
}
