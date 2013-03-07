package com.DarkXD.PlastiCraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import com.DarkXD.PlastiCraft.ServerProxy;

public class ClientProxy extends ServerProxy {
        
        @Override
        public void registerRenderers() {
                //MinecraftForgeClient.preloadTexture(ITEMS_PNG);
                MinecraftForgeClient.preloadTexture(BLOCK_PNG);
        }
        
}