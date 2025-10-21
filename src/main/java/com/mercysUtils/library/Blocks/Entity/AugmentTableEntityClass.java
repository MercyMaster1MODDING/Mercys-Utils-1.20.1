package com.mercysUtils.library.Blocks.Entity;

import com.mercysUtils.library.Blocks.Custom.AugmentTableClass;
import com.mercysUtils.library.Blocks.ModBlocks;
import com.mercysUtils.library.MercysUtils;
import com.mercysUtils.library.Screen.AugmentTableMenu;
import com.mercysUtils.library.Screen.StoveTopMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AugmentTableEntityClass extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            // Call your instant update logic here
            onInventoryChanged(slot);
        }
    };

    private void onInventoryChanged(int slot) {
        // You can check the current contents of the slots here
        ItemStack toolStack = itemStackHandler.getStackInSlot(INPUT_SLOT);
        ItemStack augment1 = itemStackHandler.getStackInSlot(AUGMENT_SLOT_ONE);
        ItemStack augment2 = itemStackHandler.getStackInSlot(AUGMENT_SLOT_TWO);

        // Immediately apply or remove augment effects based on these items
        // For example, update tool stats or abilities instantly

        // You might want to call a method on the tool or your system here

        if (!toolStack.isEmpty()) {
            // Clear previous augment effects
            clearAugments(toolStack);

            // Apply augment 1 if valid
            if (!augment1.isEmpty()) applyAugment(toolStack, augment1);

            // Apply augment 2 if valid
            if (!augment2.isEmpty()) applyAugment(toolStack, augment2);
        }
    }

    //Apply Augments to the tool/armor
    private void applyAugment(ItemStack toolStack, ItemStack augment1) {
    }

    //clears the augment effects from the slots
    private void clearAugments(ItemStack toolStack) {
    }


    private static final int INPUT_SLOT = 0;
    private static final int AUGMENT_SLOT_ONE = 1;
    private static final int AUGMENT_SLOT_TWO = 2;

    private LazyOptional<IItemHandler> handlerLazyOptional = LazyOptional.empty();

    private ContainerData data;
    private int progress = 0;
    private int maxprogress = 1;

    public AugmentTableEntityClass(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.AUGMENT_TABLE_ENTITY_CLASS_BLOCK_ENTITY_TYPE.get(), blockPos, blockState);

        this.data = new ContainerData() {
            @Override
            public int get(int num) {
                return switch (num) {
                    case 0 -> AugmentTableEntityClass.this.progress;
                    case 1 -> AugmentTableEntityClass.this.maxprogress;
                    default -> 0;

                };
            }

            public void set(int num, int value) {
                switch (num){
                    case 0 -> AugmentTableEntityClass.this.progress = value;
                    case 1 -> AugmentTableEntityClass.this.maxprogress = value;
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return handlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        handlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handlerLazyOptional.invalidate();
    }

    public void drops(){
        SimpleContainer simpleContainer = new SimpleContainer(itemStackHandler.getSlots());
        for (int num = 0; num < itemStackHandler.getSlots(); num++){
            simpleContainer.setItem(num, itemStackHandler.getStackInSlot(num));
        }
        Containers.dropContents(this.level, this.worldPosition, simpleContainer);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.mercys_utils.augment_table");
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new AugmentTableMenu(containerId, inventory, this , this.data);
    }

//    @Override
//    protected void saveAdditional(CompoundTag tag) {
//        tag.put("inventory", itemStackHandler.serializeNBT());
//        tag.putInt("augment_table_progress", progress);
//        super.saveAdditional(tag);
//    }
//
//    @Override
//    public void load(CompoundTag tag) {
//        super.load(tag);
//        itemStackHandler.deserializeNBT(tag.getCompound("inventory"));
//        progress = tag.getInt("augment_table_progress");
//    }


}
