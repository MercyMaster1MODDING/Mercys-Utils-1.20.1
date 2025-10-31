package com.mercysUtils.library.Events;

import com.mercysUtils.library.Client.InventorySorter.SortMode;
import com.mercysUtils.library.Packets.InventorySortPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InventoryButtonHandler {

    private static SortMode sortMode = SortMode.Name;
    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();

        if (screen instanceof InventoryScreen invScreen) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            InventoryButtonHandler handler = new InventoryButtonHandler(); // Create an instance

            int x = invScreen.getGuiLeft() + invScreen.getXSize() - 30;
            int y = invScreen.getGuiTop() + 65;

            Button sortButton = Button.builder(
                    Component.literal("Sort"),
                    btn -> {
                        // Cycle mode and update button label
                        SortMode next = handler.getNextMode();
                        btn.setMessage(Component.literal("Sort"));

                        // Run the sort (or send packet to server)
                        NetworkHandler.CHANNEL.sendToServer(new InventorySortPacket(next));

                    }
            ).pos(x, y).size(25, 15).build();

            event.addListener(sortButton);
        }
    }


    public SortMode getNextMode() {
        int nextIndex = (sortMode.ordinal() + 1) % SortMode.values().length;
        sortMode = SortMode.values()[nextIndex];
        return sortMode;
    }

}
