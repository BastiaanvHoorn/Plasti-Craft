package plasticraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import plasticraft.PlastiCraft;
import plasticraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidGas extends BlockFluidClassic {

	public static IIcon[] theIcon;
	
	public FluidGas(Fluid fluid, Material material) {
		super(fluid, material);
		setDensity(-1);
		setCreativeTab(PlastiCraft.tabsPC);
		setBlockTextureName(References.MOD_ID.toLowerCase() + ":fluidgas_flowing");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon){
		blockIcon = icon.registerIcon(this.getTextureName());
		theIcon = new IIcon[] {icon.registerIcon(References.MOD_ID.toLowerCase() + ":fluidgas_still"), icon.registerIcon(References.MOD_ID.toLowerCase() + ":fluidgas_flowing")};
		PlastiCraft.gasFluid.setIcons(theIcon[0], theIcon[1]);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(entity instanceof EntityPlayer){
			((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 120));
			world.handleMaterialAcceleration(entity.boundingBox.expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), PlastiCraft.plastic, entity);
			super.onEntityCollidedWithBlock(world, x, y, z, entity);
		}
	}
	
}


