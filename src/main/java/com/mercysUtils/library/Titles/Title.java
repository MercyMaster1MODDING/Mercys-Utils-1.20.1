package com.mercysUtils.library.Titles;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.List;

public class Title {

    private final String id;
    private final Component displayName;
    private final Component description;
    private final List<MobEffectInstance> effects;

    public Title(String id, Component displayName, Component description, List<MobEffectInstance> effects) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.effects = effects;
    }

    public String getId() {
        return id;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public Component getDescription() {
        return description;
    }

    public List<MobEffectInstance> getEffects() {
        return effects;
    }


}
