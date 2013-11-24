package plasticraft.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeFluidPlastic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Fluid_Plastic extends BlockFluidClassic implements ITileEntityProvider{

	public static Icon[] theIcon;
	
	public TeFluidPlastic te;	
	public Fluid_Plastic(int id, Fluid fluid, Material material) {
		super(id, fluid, material);
		setUnlocalizedName("plasticBlockfluid");
		setTextureName(References.MOD_ID.toLowerCase() + ":fluidplastic_flowing");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register){
		blockIcon = register.registerIcon(this.getTextureName());
		theIcon = new Icon[] {register.registerIcon(References.MOD_ID.toLowerCase() + ":fluidplastic_still"), register.registerIcon(References.MOD_ID.toLowerCase() + ":fluidplastic_flowing")};
		PlastiCraft.plastic_fluid.setIcons(theIcon[0], theIcon[1]);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TeFluidPlastic();
	}
	
}
	


