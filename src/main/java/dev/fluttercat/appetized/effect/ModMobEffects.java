package dev.fluttercat.appetized.effect;

import dev.fluttercat.appetized.Appetized;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffects {
    public static final Holder<MobEffect> SLASHED = registerMobEffect("slashed",
            new SlashedEffect(MobEffectCategory.HARMFUL, 0x651212));


    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(Appetized.MOD_ID, name), effect);
    }

    public static void registerModEffects() {
        Appetized.LOGGER.info("Registering Mod Effects for " + Appetized.MOD_ID);
    }
}