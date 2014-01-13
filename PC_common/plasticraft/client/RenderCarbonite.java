package plasticraft.client;

import plasticraft.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCarbonite extends RenderLiving
{
	private static ResourceLocation rs = new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/entities/Carbonite.png");
	public RenderCarbonite() {
		super(new ModelCarbonite(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rs;
	}
	
}