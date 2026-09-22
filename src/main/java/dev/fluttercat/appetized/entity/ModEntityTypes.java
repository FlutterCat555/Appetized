package dev.fluttercat.appetized.entity;

import dev.fluttercat.appetized.Appetized;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<ThrownCleaver> CLEAVER = register(
            "cleaver",
            EntityType.Builder.<ThrownCleaver>of(ThrownCleaver::new, MobCategory.MISC).noLootTable()
                    .sized(0.35f, 0.35f).clientTrackingRange(6).updateInterval(20)
    );
    
    
    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Appetized.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }
    
    public static void registerModEntityTypes() {
        Appetized.LOGGER.info("Registering EntityTypes for " + Appetized.MOD_ID);
    }
}
