package com.mercysUtils.library.Events;

import com.mercysUtils.library.Items.DimensionalTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TeleportDimensionScrollHandler {

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof DimensionalTeleporter)) return;

        if (!Screen.hasShiftDown()) return;

        int delta = (int) Math.signum(event.getScrollDelta()); // +1 or -1
        int currentIndex = DimensionalTeleporter.getSelectedIndex(stack);
        int size = DimensionalTeleporter.getAvailableDimensions(stack).size();

        // Update selected index with wrap-around
        currentIndex = (currentIndex + delta + size) % size;
        DimensionalTeleporter.setSelectedIndex(stack, currentIndex);

        // Cancel the event so it doesn’t scroll the hotbar
        event.setCanceled(true);
    }
}
