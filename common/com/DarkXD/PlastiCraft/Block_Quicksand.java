package com.DarkXD.PlastiCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Block_Quicksand extends Block {

        public Block_Quicksand (int id, int texture, Material material) {
            super(id, texture, material);
        }
        
        @Override
        public String getTextureFile () {
        	return ServerProxy.BLOCK_PNG;
        }
        
        public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
        {
            par5Entity.setInWeb();
        }

}