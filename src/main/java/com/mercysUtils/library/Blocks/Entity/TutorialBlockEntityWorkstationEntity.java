package com.mercysUtils.library.Blocks.Entity;

import com.mercysUtils.library.Items.ModItems;
import com.mercysUtils.library.Screen.TutorialBlockEntityWorkstationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TutorialBlockEntityWorkstationEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2);

    private static final int INPUT_SLOT = 0;
    private static final int Output_SLOT = 1;

    private LazyOptional<IItemHandler> handlerLazyOptional = LazyOptional.empty();

    private ContainerData data;
    private int progress = 0;
    private int maxprogress = 78;

    public TutorialBlockEntityWorkstationEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TUTORIAL_BLOCK_ENTITY_WORKSTATION_BE.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int num) {
                return switch (num){
                    case 0 -> TutorialBlockEntityWorkstationEntity.this.progress;
                    case 1 -> TutorialBlockEntityWorkstationEntity.this.maxprogress;
                        default -> 0;

                };
            }

            @Override
            public void set(int num, int value) {
                switch (num){
                    case 0 -> TutorialBlockEntityWorkstationEntity.this.progress = value;
                    case 1 -> TutorialBlockEntityWorkstationEntity.this.maxprogress = value;
                }

            }

            @Override
            public int getCount() {
                return 2;
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
        return Component.translatable("block.mercysutils.tutorial_block_entity_workstation");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TutorialBlockEntityWorkstationMenu(containerId, inventory, this , this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemStackHandler.serializeNBT());
        tag.putInt("tutorial_block_entity_workstation_progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemStackHandler.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("tutorial_block_entity_workstation_progress");
    }

    public void tick(Level level1, BlockPos blockPos, BlockState blockState1) {
        if (hasRecipe()){
            increaseCraftingProgress();
            setChanged(level1, blockPos,blockState1);

            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
            else {
                resetProgress();
            }
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {

        ItemStack result = new ItemStack(ModItems.JELLO_PIE.get(), 1);
        this.itemStackHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemStackHandler.setStackInSlot(Output_SLOT, new ItemStack(result.getItem(),
                this.itemStackHandler.getStackInSlot(Output_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe(){

        boolean hasCraftingItem = this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.GELATIN.get();
        ItemStack result = new ItemStack(ModItems.JELLO_PIE.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) &&
                canInsertItemIntoOutputSlot(result.getItem());

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemStackHandler.getStackInSlot(Output_SLOT).isEmpty() ||
                this.itemStackHandler.getStackInSlot(Output_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemStackHandler.getStackInSlot(Output_SLOT).getCount() + count <=
                this.itemStackHandler.getStackInSlot(Output_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished(){
        return progress >= maxprogress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }



    ;
}
