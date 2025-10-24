package com.mercysUtils.library.Titles;

import net.minecraft.nbt.CompoundTag;

import java.util.Set;

public interface IPlayerTitles {

    Set<String> getOwnedTitles();

    void addTitle(String titleId);

    void removeTitle(String titleId);

    boolean hasTitle(String titleId);

    String getActiveTitle();

    void setActiveTitle(String titleId);

    CompoundTag serializeNBT();

    void deserializeNBT(CompoundTag nbt);
}
