package com.mercysUtils.library.Events;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerCloneHandler {
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) return; // only handle teleportation, not respawn

        event.getEntity().getInventory().replaceWith(event.getOriginal().getInventory());
    }
}
