package net.roofedmonkey8.mixin;

import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

@Mixin(targets = "net.minecraft.world.entity.ai.gossip.GossipContainer$EntityGossips")
public class GossipMaxMixin {
    @Shadow
    private Object2IntMap<GossipType> entries;

    @Overwrite
    public void makeSureValueIsntTooLowOrTooHigh(final GossipType type) {
            int newMax;
        if (type == GossipType.MAJOR_POSITIVE) {
            newMax = 100;
        } else if (type == GossipType.MINOR_POSITIVE) {
            newMax = 125;
        } else {
            newMax = type.max;
        }

        int value = this.entries.getInt(type);
        if (value > newMax) {
            this.entries.put(type, newMax);
        }

        if (value < 2) {
            ((Object2IntOpenHashMap<GossipType>) this.entries).removeInt(type);
        }
		}
}