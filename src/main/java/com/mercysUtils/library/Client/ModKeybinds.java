package com.mercysUtils.library.Client;

import com.mercysUtils.library.Config;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.item.SpyglassItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeybinds {

    private static final Map<String, KeyMapping> KEYBINDS = new HashMap<>();
    public static KeyMapping ZOOM;

    // Example: register your keybinds here
    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        ZOOM = new KeyMapping(
                "key.mercysutils.zoom",                                           // translation key
                InputConstants.Type.KEYSYM,                                 // input type
                GLFW.GLFW_KEY_Z,                                            // default key
                "key.categories.mercysutils"                                      // category
        );
        event.register(ZOOM);
    }
    public static KeyMapping get(String name) {
        return KEYBINDS.get(name);
    }
}
