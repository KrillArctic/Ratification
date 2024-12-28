package com.krillarctic.ratify.entity.custom;

import com.krillarctic.ratify.entity.ModEntities;
import com.krillarctic.ratify.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RatEntity extends Animal {

    public final AnimationState idleAnimaitonState = new AnimationState();
    public int idleAnimationTimeout = 0;
    public RatEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 3.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.CHEESE), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));


    }
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.CHEESE.get());
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.RAT.get().create(level);
    }

    private void  setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 10;
            this.idleAnimaitonState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
