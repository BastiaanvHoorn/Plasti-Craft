package plasticraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import plasticraft.lib.References;

public class RenderClone extends RenderPlayer{

	private ResourceLocation rs = new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/entities/Clone.png");
	public RenderClone(ModelBase par1ModelBase, float par2) {
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return rs;
	}

}
