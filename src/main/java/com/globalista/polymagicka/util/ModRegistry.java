package com.globalista.polymagicka.util;

import com.mojang.serialization.Codec;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModRegistry {

    public static final Codec<SimpleParticleType> SIMPLE_PARTICLE_TYPE_CODEC = Codec.STRING.xmap(
            particleId -> (SimpleParticleType) Registries.PARTICLE_TYPE.get(Identifier.of(particleId)),  // Deserialize
            particleType -> Registries.PARTICLE_TYPE.getId(particleType).toString()  // Serialize
    );

    public static final TagKey<Item> GRIMOIRES = TagKey.of(RegistryKeys.ITEM, Helper.id("grimoires"));


}
