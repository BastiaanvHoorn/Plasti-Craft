package net.XDMods.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class modBlock extends Block{
	
	public modBlock(int par1,int par2, Material par3){
		super(par1,par2,par3);
		this.blockIndexInTexture=par2;
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public String getTextureFile(){
		return "/XDTexture/terrain.png";
	}

}
