package com.krillarctic.ratify.item;

import com.krillarctic.ratify.RatMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RatMod.MOD_ID);

    public static final DeferredItem<Item> SOULOFANINNOCENT = ITEMS.register("soulofaninnocent",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.7f).build())));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
