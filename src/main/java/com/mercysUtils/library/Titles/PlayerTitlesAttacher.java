package com.mercysUtils.library.Titles;

import com.mercysUtils.library.MercysUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID)
public class PlayerTitlesAttacher {

    private static final ResourceLocation ID = new ResourceLocation(MercysUtils.MOD_ID, "player_titles");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof net.minecraft.world.entity.player.Player) {
            event.addCapability(ID, new PlayerTitlesProvider());
        }
    }
}
