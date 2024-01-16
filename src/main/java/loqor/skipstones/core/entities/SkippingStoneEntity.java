package loqor.skipstones.core.entities;

import loqor.skipstones.core.SkipEntityTypes;
import loqor.skipstones.core.SkipItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SkippingStoneEntity
        extends ThrownItemEntity {

    public SkippingStoneEntity(EntityType<SkippingStoneEntity> entityType, World world) {
        super(entityType, world);
    }

    public SkippingStoneEntity(EntityType<? extends SkippingStoneEntity> entityType, World world, LivingEntity owner) {
        super(entityType, owner, world);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            double d = 0.08;
            for (int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isInFluid()) {
            this.setVelocity(this.getVelocity().x, this.getVelocity().y + 0.25, this.getVelocity().z);
            return;
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 0.0f);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }
                for (int j = 0; j < i; ++j) {
                    ItemEntity itemEntity = EntityType.ITEM.create(this.getWorld());
                    if (itemEntity == null) continue;
                    itemEntity.dropItem(SkipItems.SKIPPING_STONE);
                    itemEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0f);
                    this.getWorld().spawnEntity(itemEntity);
                }
            }
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.EGG;
    }
}
