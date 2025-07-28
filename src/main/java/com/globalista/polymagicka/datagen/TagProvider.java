package com.globalista.polymagicka.datagen;

import com.globalista.polymagicka.PolyMagicka;
import com.globalista.polymagicka.util.Helper;
import com.globalista.polymagicka.util.ModRegistry;
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

        getTagBuilder(ModRegistry.GRIMOIRES).add(Helper.id("test_grimoire"));
        getTagBuilder(ModRegistry.PERMITS_CASTING).addTag(Helper.id("grimoires"))
                //.addTag(Helper.id("alteration_tomes")).addTag(Helper.id("conjuration_tomes"))
                //.addTag(Helper.id("destruction_tomes")).addTag(Helper.id("illusion_tomes"))
                //.addTag(Helper.id("restoration_tomes"))
                ;

        getTagBuilder(ModRegistry.WANDS).add(Helper.id("test_wand"));
        getTagBuilder(ModRegistry.SCROLLS).add(Helper.id("test_scroll"));

    }
}

