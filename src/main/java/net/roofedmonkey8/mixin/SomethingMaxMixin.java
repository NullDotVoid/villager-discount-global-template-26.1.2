package net.roofedmonkey8.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;

@Mixin(GossipContainer.class)
public class SomethingMaxMixin {
    @Overwrite
    private int mergeValuesForAddition(final GossipType type, final int oldValue, final int newValue) {
        int newMax;
        if (type == GossipType.MAJOR_POSITIVE) {
            newMax = 100;
        } else if (type == GossipType.MINOR_POSITIVE) {
            newMax = 125;
        } else {
            newMax = type.max;
        }
        int sum = oldValue + newValue;
        return sum > newMax ? Math.max(newMax, oldValue) : sum;
        }
}
