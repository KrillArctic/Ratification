package com.krillarctic.ratify.datagen;

import com.krillarctic.ratify.RatMod;
import com.krillarctic.ratify.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemsModelProvider extends ItemModelProvider {
    public ModItemsModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, RatMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CHEESE.get());
        basicItem(ModItems.SOULOFANINNOCENT.get());

        withExistingParent(ModItems.RAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
