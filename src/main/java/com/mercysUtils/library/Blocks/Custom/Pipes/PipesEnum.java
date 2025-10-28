package com.mercysUtils.library.Blocks.Custom.Pipes;

import net.minecraft.util.StringRepresentable;

/**
 * Represents the different visual pipe shapes that can appear depending on
 * the number and orientation of connections.
 *
 * SINGLE  - One or no connections (end cap)
 * CONNECTING_LONG - Two opposite connections (straight pipe)
 * CORNER  - Two perpendicular connections (corner piece)
 * MIDDLE  - Three or more connections (junction)
 */
public enum PipesEnum implements StringRepresentable {
    SINGLE("pipe_single"),
    CONNECTING_LONG("pipe_connecting_long"),
    CORNER("pipe_corner"),
    MIDDLE("pipe_middle");

    private final String name;

    PipesEnum(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
