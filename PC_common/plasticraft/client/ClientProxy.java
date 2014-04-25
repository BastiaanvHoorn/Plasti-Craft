package plasticraft.client;

import plasticraft.CommonProxy;
import plasticraft.client.interfaces.GuiHandler;
import plasticraft.entity.EntityCarbonite;
import plasticraft.entity.EntityClone;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers(){
		new GuiHandler();
		RenderingRegistry.registerEntityRenderingHandler(EntityCarbonite.class, new RenderCarbonite());
		RenderingRegistry.registerEntityRenderingHandler(EntityClone.class, new RenderClone(new ModelClone(0.5F)));
	}
        
}