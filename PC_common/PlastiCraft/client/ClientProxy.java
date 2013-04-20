package PlastiCraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import PlastiCraft.CommonProxy;

public class ClientProxy extends CommonProxy {
        
        @SuppressWarnings("deprecation")
		@Override
        public void registerRenderers() {
                MinecraftForgeClient.preloadTexture(TEXTURES_PNG);
        }
        
}