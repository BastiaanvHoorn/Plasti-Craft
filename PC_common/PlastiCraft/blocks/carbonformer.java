package PlastiCraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class carbonformer extends Block {

	public carbonformer(int id, Material Material) {
		super(id, Material);
		setStepSound(soundStoneFootstep);
		setHardness(2F);
		setLightValue(0.1F);
	}

}
