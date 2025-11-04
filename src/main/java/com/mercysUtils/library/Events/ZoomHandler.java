package com.mercysUtils.library.Events;

import com.mercysUtils.library.Client.ModKeybinds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ZoomHandler {

    // Zoom configuration
    private static final float ZOOM_FOV_MULTIPLIER = 0.25f;  // lower = stronger zoom
    private static final float SMOOTH_SPEED = 0.08f;         // smaller = smoother transition

    private static boolean zooming = false;
    private static float currentZoom = 1.0f;

    // Detect key state every tick (20 Hz)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (ModKeybinds.ZOOM == null) return;

        zooming = ModKeybinds.ZOOM.isDown();
    }

    // Smoothly adjust FOV every frame (called much more often than ticks)
    @SubscribeEvent
    public static void onFovCompute(ViewportEvent.ComputeFov event) {
        float targetZoom = zooming ? ZOOM_FOV_MULTIPLIER : 1.0f;

        // partialTick gives us smooth interpolation between ticks
        float delta = (float) event.getPartialTick();
        currentZoom += (targetZoom - currentZoom) * SMOOTH_SPEED * (1.0f + delta);

        event.setFOV(event.getFOV() * currentZoom);
    }

    public static boolean isZooming() {
        return zooming;
    }
}
