package com.mercysUtils.library.Events;

import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Titles.PlayerTitlesProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID)
public class CapabilityEventHandler {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Player> event) {
        if (!event.getObject().getCapability(PlayerTitlesProvider.PLAYER_TITLES).isPresent()) {
            event.addCapability(new ResourceLocation(MercysUtils.MOD_ID, "player_titles"),
                    new PlayerTitlesProvider());
        }
    }
}
