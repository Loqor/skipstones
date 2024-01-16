package loqor.skipstones.client;

import loqor.skipstones.core.SkipEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.projectile.thrown.ThrownEntity;

@Environment(value = EnvType.CLIENT)
public class SkipstonesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEntityRenderers();
    }

    public void registerEntityRenderers() {
        EntityRendererRegistry.register(SkipEntityTypes.SKIPPING_STONE_ENTITY_TYPE, FlyingItemEntityRenderer::new);
    }
}
