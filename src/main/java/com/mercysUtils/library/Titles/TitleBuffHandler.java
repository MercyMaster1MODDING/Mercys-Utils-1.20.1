package com.mercysUtils.library.Titles;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.mercysUtils.library.MercysUtils;

@Mod.EventBusSubscriber(modid = MercysUtils.MOD_ID)
public class TitleBuffHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide()) return;
        if (player.isSpectator() || player.isCreative()) return;

        applyTitleBuffs(player);
    }

    public static void applyTitleBuffs(Player player) {
        // First, remove all previous title effects
        player.getCapability(PlayerTitlesProvider.PLAYER_TITLES).ifPresent(titles -> {

        // Apply the active title's effects
            String activeTitleId = titles.getActiveTitle();
            if (activeTitleId != null && !activeTitleId.isEmpty()) {
                Title activeTitle = TitleRegistry.getTitleById(activeTitleId);
                if (activeTitle != null) {
                    for (MobEffectInstance effect : activeTitle.getEffects()) {
                        player.addEffect(new MobEffectInstance(effect));
                    }
                }
            }
        });
    }

//    private static void clearTitleBuffs(Player player) {
//        player.getCapability(PlayerTitlesProvider.PLAYER_TITLES).ifPresent(titles -> {
//            for (String titleId : titles.getOwnedTitles()) {
//                Title title = TitleRegistry.getTitleById(titleId);
//                if (title != null) {
//                    for (MobEffectInstance effect : title.getEffects()) {
//                        // Remove the effect instance
//                        player.removeEffect(effect.getEffect());
//
//                        // Reset health if the effect modifies max health
//                        if (effect.getEffect() == MobEffects.HEALTH_BOOST ||
//                                effect.getEffect() == MobEffects.ABSORPTION) {
//                            if (player.getHealth() > player.getMaxHealth()) {
//                                player.setHealth(player.getMaxHealth());
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }


}
