package plasticraft.blocks;

import plasticraft.PlastiCraft;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;

public class BlockQuicksandMoving  extends BlockFlowing implements ILiquid{

	public BlockQuicksandMoving(int i, Material material){
		super(i, material);
		setHardness(100F);
		
	}

	@Override
	public int stillLiquidId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMetaSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
