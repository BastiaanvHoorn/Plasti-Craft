package plasticraft.client;

import plasticraft.CommonProxy;
import plasticraft.client.interfaces.GuiHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers(){
		new GuiHandler();
	}
        
}