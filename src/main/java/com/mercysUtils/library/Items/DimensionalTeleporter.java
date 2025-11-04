package com.mercysUtils.library.Items;

import com.mercysUtils.library.Worldgen.Dimension.CandyDimension;
import com.mercysUtils.library.Worldgen.Dimension.ModDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class DimensionalTeleporter extends Item {

    public DimensionalTeleporter(Properties properties) {
        super(properties);
    }

    // Get or create the selected dimension index in the ItemStack NBT
    public static int getSelectedIndex(ItemStack stack) {
        if (stack.getOrCreateTag().contains("SelectedIndex")) {
            return stack.getTag().getInt("SelectedIndex");
        }
        stack.getOrCreateTag().putInt("SelectedIndex", 0);
        return 0;
    }

    public static void setSelectedIndex(ItemStack stack, int index) {
        stack.getOrCreateTag().putInt("SelectedIndex", index);
    }

    // Available dimensions for this teleporter
    public static List<String> getAvailableDimensions(ItemStack stack) {
        return List.of(
                "mercysutils:mercys_dimension",
                "minecraft:overworld",
                "mercysutils:candy_dimension"
        );
    }

    public static String getSelectedDimension(ItemStack stack) {
        List<String> dims = getAvailableDimensions(stack);
        int index = getSelectedIndex(stack);
        index = Math.max(0, Math.min(index, dims.size() - 1));
        return dims.get(index);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            ItemStack stack = player.getItemInHand(hand);
            String dimensionId = getSelectedDimension(stack);


            ServerLevel targetLevel;
            switch (dimensionId) {
                case "minecraft:overworld" -> targetLevel = serverPlayer.getServer().getLevel(Level.OVERWORLD);
                case "mercysutils:candy_dimension" -> targetLevel = serverPlayer.getServer().getLevel(CandyDimension.CANDY_DIMENSION_LEVEL_KEY);
                case "mercysutils:mercys_dimension" -> targetLevel = serverPlayer.getServer().getLevel(ModDimension.MERCYS_DIMENSION_LEVEL_KEY);
                default -> {
                    player.displayClientMessage(Component.literal("Unknown dimension!"), true);
                    return InteractionResultHolder.fail(stack);
                }
            }

            ResourceKey<Level> currentKey = serverPlayer.level().dimension();
            ResourceKey<Level> targetKey = targetLevel.dimension();

            System.out.println("Current: " + serverPlayer.level().dimension().location());
            System.out.println("Target: " + targetLevel.dimension().location());


            if (currentKey == targetKey) {
                player.displayClientMessage(Component.literal("§cYou are already in this dimension!"), true);
                return InteractionResultHolder.fail(stack);
            }


            teleportPlayer(serverPlayer, targetLevel);

        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }

    private void teleportPlayer(ServerPlayer player, ServerLevel targetLevel) {
        BlockPos spawnPos = targetLevel.getSharedSpawnPos(); // overworld or custom spawn
        player.changeDimension(targetLevel);
        player.teleportTo(targetLevel,
                spawnPos.getX() + 0.5,
                spawnPos.getY(),
                spawnPos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot()
        );
    }

    public static String getDisplayName(String dimensionId) {
        return switch (dimensionId) {
            case "minecraft:overworld" -> "Overworld";
            case "mercysutils:candy_dimension" -> "Candy Dimension";
            case "mercysutils:mercys_dimension" -> "Mercy’s Dimension";
            default -> "Unknown";
        };
    }

}
