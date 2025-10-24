package com.mercysUtils.library.Titles;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;

public class PlayerTitlesProvider implements ICapabilityProvider {

    public static final Capability<IPlayerTitles> PLAYER_TITLES =
            net.minecraftforge.common.capabilities.CapabilityManager.get(new net.minecraftforge.common.capabilities.CapabilityToken<>() {});


    private final PlayerTitles instance = new PlayerTitles();
    private final LazyOptional<IPlayerTitles> optional = LazyOptional.of(() -> instance);

    public static Capability<IPlayerTitles> getPlayerTitlesCap() {
        return PLAYER_TITLES;
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_TITLES) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }
}

