package com.mercysUtils.library.Titles;

import com.mercysUtils.library.CustomEffects.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleRegistry {

    private static final Map<String, Title> TITLES = new HashMap<>();

    public static final Title KNIGHT = register(new Title(
            "knight",
            Component.literal("Knight"),
            Component.literal("A brave warrior title"),
            List.of(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2, 0, true, true))
    ));

    public static final Title MAGE = register(new Title(
            "mage",
            Component.literal("Mage"),
            Component.literal("Master of the arcane arts"),
            List.of(new MobEffectInstance(MobEffects.REGENERATION, 2, 2, true, true))
    ));

    public static final Title GLUTTON = register(new Title(
            "glutton",
            Component.literal("Glutton"),
            Component.literal("Just a glutton"),
            List.of(new MobEffectInstance(MobEffects.ABSORPTION, 2, 8, true, true))
    ));
    public static final Title EAGLE = register(new Title(
            "eagle",
            Component.literal("Eagle"),
            Component.literal("Great eyesight"),
            List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 2, 1, true, true))
    ));
    public static final Title TANK = register(new Title(
            "tank",
            Component.literal("Tank"),
            Component.literal("Bulky lol"),
            List.of(new MobEffectInstance(MobEffects.ABSORPTION, 2, 5, true, true),
                    new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 3, true, true),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 2, 6, true, true))
    ));

    public static Title register(Title title) {
        TITLES.put(title.getId(), title);
        return title;
    }

    public static Title getTitleById(String id) {
        return TITLES.get(id);
    }

    public static Map<String, Title> getAllTitles() {
        return TITLES;
    }
}
