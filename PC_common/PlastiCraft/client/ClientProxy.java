package plasticraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import plasticraft.CommonProxy;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {
                MinecraftForgeClient.preloadTexture(TEXTURES_PNG);
        }
        
}