package PlastiCraft.blocks;


import PlastiCraft.PlastiCraft;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.liquids.ILiquid;

public class BlockQuicksandStill extends BlockStationary implements ILiquid{

	public BlockQuicksandStill(int i, Material material){
		super(i, material);
		setHardness(100F);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("Quicksand liquid");
	}
	


	@Override
	public int stillLiquidId() {
		return 501;
	}

	@Override
	public boolean isMetaSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}
	
	
	
}
