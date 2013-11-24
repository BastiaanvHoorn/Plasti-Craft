package plasticraft.blocks;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPlastic extends Block {

	public BlockPlastic(int par1) {
		super(par1, Material.iron);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("PlasticBlock");
		setTextureName(References.MOD_ID +":BlockPlastic");
		setStepSound(Block.soundMetalFootstep);
		setHardness(2F);
		setResistance(0.5F);
	}

}
