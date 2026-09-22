package dev.fluttercat.appetized;

import dev.fluttercat.appetized.entity.ModEntityTypes;
import dev.fluttercat.appetized.entity.ThrownCleaver;
import dev.fluttercat.appetized.entity.ThrownCleaverRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class AppetizedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntityTypes.CLEAVER, ThrownCleaverRenderer::new);
    }
}
