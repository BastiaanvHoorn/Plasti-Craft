package plasticraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class BlockPlastic extends Block {
	//the class to make one instance for the Block of PLastic
	public BlockPlastic() {
		//sets the variables specifically for this block
		super(Material.iron);
		setCreativeTab(PlastiCraft.tabsPC);
		setBlockTextureName(References.MOD_ID +":BlockPlastic");
		setStepSound(Block.soundTypeMetal);
		setHardness(2F);
		setResistance(0.5F);
	}

}
