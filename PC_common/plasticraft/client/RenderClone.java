package plasticraft.client;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import plasticraft.lib.References;

public class RenderClone extends RendererLivingEntity{

	private ResourceLocation rs = new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/entities/Clone.png");
	public RenderClone() {
		super(new ModelClone(0.0F), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return rs;
	}

}
