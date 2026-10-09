package org.kurw1k.create_slag_leech.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal; // Или Monster, если враждебный
import net.minecraft.world.level.Level;

public class SlagLeechEntity extends WaterAnimal { // Или Monster, если хотите сделать враждебным

    public SlagLeechEntity(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    // Задаем базовые атрибуты моба (здоровье, скорость и т.д.)
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
}