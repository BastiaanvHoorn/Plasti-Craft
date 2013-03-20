package plasticraft.blocks;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraftforge.liquids.ILiquid;

public class Quicksand_Moving  extends BlockFlowing{

	public Quicksand_Moving(int i, Material material){
		super(i, material);
		setHardness(100F);
	}
}
