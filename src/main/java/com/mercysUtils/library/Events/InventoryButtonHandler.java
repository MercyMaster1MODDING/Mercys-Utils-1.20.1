package com.mercysUtils.library.Events;

import com.mercysUtils.library.Client.InventorySorter.SortMode;
import com.mercysUtils.library.Packets.InventorySortPacket;
import com.mercysUtils.library.Events.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ChestMenu;
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // --- Player Inventory ---
        if (screen instanceof InventoryScreen invScreen) {
            int x = invScreen.getGuiLeft() + invScreen.getXSize() - 30;
            int y = invScreen.getGuiTop() + 65;

            Button sortButton = Button.builder(
                    Component.literal("Sort"),
                    btn -> {
                        SortMode next = getNextMode();
                        btn.setMessage(Component.literal("Sort"));
                        NetworkHandler.CHANNEL.sendToServer(new InventorySortPacket(next));
                    }
            ).pos(x, y).size(25, 15).build();

            event.addListener(sortButton);
        }

        // --- Chest (single or double) ---
        else if (screen instanceof AbstractContainerScreen<?> containerScreen
                && containerScreen.getMenu() instanceof ChestMenu chestMenu) {

            Container chestContainer = chestMenu.getContainer();
            if (chestContainer.getContainerSize() < 54){
                int x = containerScreen.getGuiLeft() + containerScreen.getXSize() - 30;
                int y = containerScreen.getGuiTop();

            Button sortButton = Button.builder(
                    Component.literal("Sort"),
                    btn -> {
                        SortMode next = getNextMode();
                        btn.setMessage(Component.literal("Sort"));
                        NetworkHandler.CHANNEL.sendToServer(new InventorySortPacket(next));
                    }
            ).pos(x, y).size(25, 15).build();

            event.addListener(sortButton);
            }
            else if (chestContainer.getContainerSize() >= 54) { // only double chests
                int x = containerScreen.getGuiLeft() + containerScreen.getXSize() - 30;
                int y = containerScreen.getGuiTop();
                        //+ 125;

                Button sortButton = Button.builder(
                        Component.literal("Sort"),
                        btn -> {
                            SortMode next = getNextMode();
                            btn.setMessage(Component.literal("Sort"));
                            NetworkHandler.CHANNEL.sendToServer(new InventorySortPacket(next));
                        }
                ).pos(x, y).size(25, 15).build();

                event.addListener(sortButton);
            }
        }


    }

    private static SortMode getNextMode() {
        int nextIndex = (sortMode.ordinal() + 1) % SortMode.values().length;
        sortMode = SortMode.values()[nextIndex];
        return sortMode;
    }
}
