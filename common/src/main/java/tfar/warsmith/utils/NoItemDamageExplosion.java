package tfar.warsmith.utils;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class NoItemDamageExplosion extends Explosion {

    public NoItemDamageExplosion(Level level, @Nullable Entity entity, @Nullable DamageSource $$2, @Nullable ExplosionDamageCalculator $$3, double $$4, double $$5, double $$6, float $$7, boolean $$8, BlockInteraction $$9) {
        super(level, entity, $$2, $$3, $$4, $$5, $$6, $$7, $$8, $$9);
        this.level = level;
        this.entity = entity;
    }

    protected final Level level;
    protected final Entity entity;

    public static Explosion explode(Level level,
            @Nullable Entity entity,
            @Nullable DamageSource $$1,
            @Nullable ExplosionDamageCalculator $$2,
            double $$3,
            double $$4,
            double $$5,
            float $$6,
            boolean $$7,
            Level.ExplosionInteraction interaction,
            boolean $$9
    ) {
        Explosion.BlockInteraction $$10 = switch(interaction) {
            case NONE -> Explosion.BlockInteraction.KEEP;
            case BLOCK -> level.getDestroyType(GameRules.RULE_BLOCK_EXPLOSION_DROP_DECAY);
            case MOB -> level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                    ? level.getDestroyType(GameRules.RULE_MOB_EXPLOSION_DROP_DECAY)
                    : Explosion.BlockInteraction.KEEP;
            case TNT -> level.getDestroyType(GameRules.RULE_TNT_EXPLOSION_DROP_DECAY);
        };
        Explosion $$11 = new NoItemDamageExplosion(level, entity, $$1, $$2, $$3, $$4, $$5, $$6, $$7, $$10);
        $$11.explode();
        $$11.finalizeExplosion($$9);
        return $$11;
    }

}
