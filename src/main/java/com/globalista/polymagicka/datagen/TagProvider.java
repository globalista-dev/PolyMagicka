package com.globalista.polymagicka.datagen;

import com.globalista.polymagicka.util.Helper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class TagProvider extends FabricTagProvider<Item> {
    public TagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {



    }
}

