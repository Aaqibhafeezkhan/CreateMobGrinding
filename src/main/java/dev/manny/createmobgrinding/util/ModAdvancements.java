package dev.manny.createmobgrinding.util;

import dev.manny.createmobgrinding.CreateMobGrinding;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * Awards advancements that use the impossible trigger, for actions no vanilla trigger can detect.
 */
public final class ModAdvancements {
    public static final String CRAFT_SPAWNER_CASING = "craft_spawner_casing";
    public static final String STACK_CHUNKS = "stack_chunks";
    public static final String USE_CHUNK_BAG = "use_chunk_bag";
    public static final String INSTALL_FAN_FILTER = "install_fan_filter";
    public static final String VACUUM_FILTER = "vacuum_filter";

    private ModAdvancements() {}

    public static void award(Player player, String name) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        AdvancementHolder advancement = serverPlayer.server.getAdvancements()
                .get(ResourceLocation.fromNamespaceAndPath(CreateMobGrinding.MOD_ID, name));
        if (advancement == null) return;
        AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
        if (progress.isDone()) return;
        for (String criterion : progress.getRemainingCriteria()) {
            serverPlayer.getAdvancements().award(advancement, criterion);
        }
    }
}
