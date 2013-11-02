package plasticraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Fluid_Plastic extends BlockFluidClassic{

	public Fluid_Plastic(int id, Fluid fluid, Material material) {
		super(id, fluid, material);
		setCreativeTab(PlastiCraft.tabsPC);
		setUnlocalizedName("plasticBlockfluid");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register){
		blockIcon = register.registerIcon(References.MOD_ID.toLowerCase() + ":fluidplastic");
	}
	

}
