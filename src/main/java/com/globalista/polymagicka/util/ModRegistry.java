package com.globalista.polymagicka.util;

import com.globalista.polymagicka.magic.components.projectiles.FreezingSnowballEntity;
import com.globalista.polymagicka.magic.components.projectiles.SpellProjectileEntity;
import com.globalista.polymagicka.magic.summons.*;
import com.mojang.serialization.Codec;
import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModRegistry {

    public static final Codec<SimpleParticleType> SIMPLE_PARTICLE_TYPE_CODEC = Codec.STRING.xmap(
            particleId -> (SimpleParticleType) Registries.PARTICLE_TYPE.get(Identifier.of(particleId)),  // Deserialize
            particleType -> Registries.PARTICLE_TYPE.getId(particleType).toString()  // Serialize
    );

    public static final TagKey<Item> PERMITS_CASTING = TagKey.of(RegistryKeys.ITEM, Helper.id("permits_casting"));
    public static final TagKey<Item> GRIMOIRES = TagKey.of(RegistryKeys.ITEM, Helper.id("grimoires"));
    public static final TagKey<Item> ALTERATION_TOMES = TagKey.of(RegistryKeys.ITEM, Helper.id("alteration_tomes"));
    public static final TagKey<Item> CONJURATION_TOMES = TagKey.of(RegistryKeys.ITEM, Helper.id("conjuration_tomes"));
    public static final TagKey<Item> DESTRUCTION_TOMES = TagKey.of(RegistryKeys.ITEM, Helper.id("destruction_tomes"));
    public static final TagKey<Item> ILLUSION_TOMES = TagKey.of(RegistryKeys.ITEM, Helper.id("illusion_tomes"));
    public static final TagKey<Item> RESTORATION_TOMES = TagKey.of(RegistryKeys.ITEM, Helper.id("restoration_tomes"));
    public static final TagKey<Item> WANDS = TagKey.of(RegistryKeys.ITEM, Helper.id("wands"));
    public static final TagKey<Item> SCROLLS = TagKey.of(RegistryKeys.ITEM, Helper.id("scroll"));

    public static final EntityType<RaisedZombie> RAISED_ZOMBIE = register(Helper.id("raised_zombie"),
            EntityType.Builder.<RaisedZombie>create(RaisedZombie::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<RaisedSkeleton> RAISED_SKELETON = register(Helper.id("raised_skeleton"),
            EntityType.Builder.<RaisedSkeleton>create(RaisedSkeleton::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<Familiar> FAMILIAR = register(Helper.id("familiar"),
            EntityType.Builder.<Familiar>create(Familiar::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<GreaterFamiliar> GREATER_FAMILIAR = register(Helper.id("greater_familiar"),
            EntityType.Builder.<GreaterFamiliar>create(GreaterFamiliar::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<FlameAtronach> FLAME_ATRONACH = register(Helper.id("flame_atronach"),
            EntityType.Builder.<FlameAtronach>create(FlameAtronach::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<WindAtronach> WIND_ATRONACH = register(Helper.id("wind_atronach"),
            EntityType.Builder.<WindAtronach>create(WindAtronach::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<LivingArmor> LIVING_ARMOR = register(Helper.id("living_armor"),
            EntityType.Builder.<LivingArmor>create(LivingArmor::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<SwarmSpider> SWARM_SPIDER = register(Helper.id("swarm_spider"),
            EntityType.Builder.<SwarmSpider>create(SwarmSpider::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<Illusion> ILLUSION = register(Helper.id("illusion"),
            EntityType.Builder.<Illusion>create(Illusion::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<SpellProjectileEntity> SPELL_PROJECTILE = register(Helper.id("spell_projectile"),
            EntityType.Builder.<SpellProjectileEntity>create(SpellProjectileEntity::new, SpawnGroup.MISC).dimensions(1f, 1f));

    public static final EntityType<FreezingSnowballEntity> FREEZING_SNOWBALL = register(Helper.id("freezing_snowball"),
            EntityType.Builder.<FreezingSnowballEntity>create(FreezingSnowballEntity::new, SpawnGroup.MISC).dimensions(1f, 1f));

    private static <T extends Entity> EntityType<T> register(Identifier provoker, EntityType.Builder<T> build) {
        var type = Registry.register(Registries.ENTITY_TYPE, provoker, build.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, provoker)));
        PolymerEntityUtils.registerType(type);
        return type;
    }

    public static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Helper.id(name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(RAISED_ZOMBIE, RaisedZombie.createAttributes());
        FabricDefaultAttributeRegistry.register(RAISED_SKELETON, RaisedSkeleton.createAttributes());
        FabricDefaultAttributeRegistry.register(FAMILIAR, Familiar.createAttributes());
        FabricDefaultAttributeRegistry.register(GREATER_FAMILIAR, GreaterFamiliar.createAttributes());
        FabricDefaultAttributeRegistry.register(FLAME_ATRONACH, FlameAtronach.createAttributes());
        FabricDefaultAttributeRegistry.register(WIND_ATRONACH, WindAtronach.createAttributes());
        FabricDefaultAttributeRegistry.register(LIVING_ARMOR, LivingArmor.createAttributes());
        FabricDefaultAttributeRegistry.register(SWARM_SPIDER, SwarmSpider.createAttributes());
        FabricDefaultAttributeRegistry.register(ILLUSION, Illusion.createAttributes());

    }


}
