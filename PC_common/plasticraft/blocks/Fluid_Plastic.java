package plasticraft.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import plasticraft.tileentities.TeFluidPlastic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Fluid_Plastic extends BlockFluidClassic implements ITileEntityProvider{

	public static IIcon[] theIcon;
	
	public TeFluidPlastic te;	
	public Fluid_Plastic(Fluid fluid, Material material) {
		super(fluid, material);
		setBlockTextureName(References.MOD_ID.toLowerCase() + ":fluidplastic_flowing");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register){
		blockIcon = register.registerIcon(this.getTextureName());
		theIcon = new IIcon[] {register.registerIcon(References.MOD_ID.toLowerCase() + ":fluidplastic_still"), register.registerIcon(References.MOD_ID.toLowerCase() + ":fluidplastic_flowing")};
		PlastiCraft.plastic_fluid.setIcons(theIcon[0], theIcon[1]);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		entity.setFire(15);
		entity.handleWaterMovement();
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TeFluidPlastic();
	}
	
}
	


