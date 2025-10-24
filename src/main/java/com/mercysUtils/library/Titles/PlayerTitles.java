package com.mercysUtils.library.Titles;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;

import java.util.HashSet;
import java.util.Set;

public class PlayerTitles implements IPlayerTitles {
    private Set<String> ownedTitles = new HashSet<>();
    private String activeTitle = "";

    @Override
    public Set<String> getOwnedTitles() {
        return Set.of();
    }

    @Override
    public void addTitle(String titleId) {
        ownedTitles.add(titleId);
    }

    @Override
    public void removeTitle(String titleId) {
        ownedTitles.remove(titleId);
        if (activeTitle.equals(titleId)) {
            activeTitle = "";
        }
    }

    @Override
    public boolean hasTitle(String titleId) {
        return ownedTitles.contains(titleId);
    }

    @Override
    public String getActiveTitle() {
        return activeTitle;
    }

    @Override
    public void setActiveTitle(String titleId) {
        if (ownedTitles.contains(titleId)) {
            activeTitle = titleId;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();

        for (String title : ownedTitles) {
            list.add(StringTag.valueOf(title));
        }
        nbt.put("OwnedTitles", list);
        nbt.putString("ActiveTitle", activeTitle);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ownedTitles.clear();
        ListTag list = nbt.getList("OwnedTitles", 8); // 8 = StringTag type
        for (int i = 0; i < list.size(); i++) {
            ownedTitles.add(list.getString(i));
        }
        activeTitle = nbt.getString("ActiveTitle");
    }
}
