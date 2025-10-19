package com.mercysUtils.library.Items;

import com.mercysUtils.library.Worldgen.Dimension.CandyDimension;
import com.mercysUtils.library.Worldgen.Dimension.ModDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

public class DimensionalTeleporter extends Item {

    public boolean CandyDimensionUnlocked = false;
    public boolean MercysDimensionUnlocked = false;
    public boolean CandyDimensionSelected = false;
    private String reason;

    public DimensionalTeleporter(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);

        // Only run on server side (to avoid running twice)
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {

            // Get the player's current coordinates
            int x = (int) player.getX();
            int y = (int) player.getY();
            int z = (int) player.getZ();

            ServerLevel targetLevel = null;

            // Check if dimension is unlocked and selected (you can expand this logic)
            if (!CandyDimensionUnlocked && !CandyDimensionSelected) {
                targetLevel = serverPlayer.server.getLevel(CandyDimension.CANDY_DIMENSION_LEVEL_KEY);
            } else {
                abortTeleport(serverPlayer, "Dimension not unlocked or selected");
                return InteractionResultHolder.fail(stack);
            }

            if (targetLevel == null) {
                abortTeleport(serverPlayer, "Target dimension not found");
                return InteractionResultHolder.fail(stack);
            }

            TeleportToDimension(serverPlayer, targetLevel, x, y, z, interactionHand);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }


    private boolean isSafeSpot(ServerLevel level, BlockPos pos) {
        // Check block player stands on is solid
        boolean solidGround = !level.getBlockState(pos.below()).isAir();

        // Check current block and block above are empty
        boolean hasHeadroom =
                level.getBlockState(pos).getCollisionShape(level, pos).isEmpty() &&
                        level.getBlockState(pos.above()).getCollisionShape(level, pos.above()).isEmpty();

        return solidGround && hasHeadroom;
    }

    private BlockPos findSafeTeleportPos(ServerLevel level, BlockPos startPos) {
        // Clamp to world height
        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight() - 2; // leave space for head

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        // If starting position is underground or unsafe, move upward until clear
        while (pos.getY() < maxY) {
            if (isSafeSpot(level, pos)) {
                return pos.immutable();
            }
            pos.move(0, 1, 0);
        }

        // If we hit max height without finding a spot, try searching downward
        pos.setY((int) Math.min(startPos.getY(), maxY));
        while (pos.getY() > minY) {
            if (isSafeSpot(level, pos)) {
                return pos.immutable();
            }
            pos.move(0, -1, 0);
        }

        // Fallback: use the world spawn if nothing is safe
        return level.getSharedSpawnPos();
    }

    private void TeleportToDimension(ServerPlayer player, ServerLevel targetLevel, int x, int y, int z, InteractionHand interactionHand) {

        ItemStack heldStack = player.getItemInHand(interactionHand);
        //&& CandyDimensionUnlocked && CandyDimensionSelected
        //the above line goes on the below line as an additional check
        //Repeat for all dimensions

        if (heldStack.getItem() instanceof DimensionalTeleporter) {
            // This is the teleporter item — proceed to get candy dimension
            targetLevel = player.server.getLevel(CandyDimension.CANDY_DIMENSION_LEVEL_KEY);
        } else {
            // Not holding the teleporter — abort or ignore
            abortTeleport(player, "You must hold the teleporter item");
            return;
        }

        x = (int) player.getX();
        y = (int) player.getY();
        z = (int) player.getZ();

        x = x * 2;
        y = y * 2;
        z = z * 2;

        // Find a safe position near (x, y, z)
        BlockPos safePos = findSafeTeleportPos(targetLevel, new BlockPos(x, y, z));

        player.changeDimension(targetLevel);
        player.teleportTo(targetLevel,
                safePos.getX() + 0.5,
                safePos.getY(),
                safePos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot());

        targetLevel.playSound(null, safePos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

     void abortTeleport(ServerPlayer player, String reason) {
        // Plays a sound, sends message, and stops the teleport
        player.level().playSound(null, player.blockPosition(),
                SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 1.0F, 1.0F);

        player.displayClientMessage(
                Component.literal("§cTeleportation failed: " + reason),
                true
        );
    }


}
