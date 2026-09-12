package dev.fluttercat.appetized.item;

import net.minecraft.world.item.component.Consumable;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModConsumables {
    public static final Consumable SLICED_BREAD = defaultFood().consumeSeconds(0.9F).build();
}
