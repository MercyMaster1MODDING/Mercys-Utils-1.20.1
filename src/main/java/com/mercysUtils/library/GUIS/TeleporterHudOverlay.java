package com.mercysUtils.library.GUIS;

import com.mercysUtils.library.Items.DimensionalTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class TeleporterHudOverlay {

    @SubscribeEvent
    public static void ShowTeleportHud(RenderGuiOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof DimensionalTeleporter)) return;

        List<String> dimensions = DimensionalTeleporter.getAvailableDimensions(stack);
        int size = dimensions.size();
        if (size == 0) return;

        int selectedIndex = DimensionalTeleporter.getSelectedIndex(stack);

        // --- Slot and layout config ---
        int slotWidth = 16;
        int slotHeight = 16;
        int gap = slotWidth / 2;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int centerX = screenWidth / 2;

        int crosshairY = screenHeight / 2;
        int healthBarY = screenHeight - 40;
        int y = (crosshairY + healthBarY) / 2 - (slotHeight / 2);

        ResourceLocation SLOT_TEXTURE = new ResourceLocation("mercysutils", "textures/gui/temp_slot.png");
        GuiGraphics guiGraphics = event.getGuiGraphics();

        // Fixed X positions
        int xMiddle = centerX - (slotWidth / 2);
        int xLeft = xMiddle - slotWidth - gap;
        int xRight = xMiddle + slotWidth + gap;

        // --- Calculate wrapped indices ---
        int leftIndex = (selectedIndex - 1 + size) % size;
        int rightIndex = (selectedIndex + 1) % size;

        // --- Draw Slots ---
        guiGraphics.blit(SLOT_TEXTURE, xLeft, y, 0, 0, slotWidth, slotHeight);
        guiGraphics.blit(SLOT_TEXTURE, xMiddle, y, 0, 0, slotWidth, slotHeight);
        guiGraphics.blit(SLOT_TEXTURE, xRight, y, 0, 0, slotWidth, slotHeight);

        // --- Draw only middle slot text ---
        drawCenterText(mc, guiGraphics, dimensions.get(selectedIndex), xMiddle, y);
    }

    private static void drawCenterText(Minecraft mc, GuiGraphics guiGraphics, String dimensionId, int slotX, int slotY) {
        String name = DimensionalTeleporter.getDisplayName(dimensionId);
        Component text = Component.literal(name);
        int textWidth = mc.font.width(text);
        int textX = slotX + (16 / 2) - (textWidth / 2);
        int textY = slotY - 12;
        guiGraphics.drawString(mc.font, text, textX + 1, textY + 1, 0x000000, false); // shadow
        guiGraphics.drawString(mc.font, text, textX, textY, 0xFFFFFF, false); // main
    }
}
