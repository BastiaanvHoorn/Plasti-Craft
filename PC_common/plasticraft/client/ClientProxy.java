package plasticraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import plasticraft.CommonProxy;
import plasticraft.client.interfaces.GuiHandler;
import plasticraft.entity.EntityCarbonite;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers(){
		new GuiHandler();
		RenderingRegistry.registerEntityRenderingHandler(EntityCarbonite.class, new RenderCarbonite());
	}
        
}