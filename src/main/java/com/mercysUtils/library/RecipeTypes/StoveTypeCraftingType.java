package com.mercysUtils.library.RecipeTypes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mercysUtils.library.MercysUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class StoveTypeCraftingType implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputlist;
    private final ItemStack output;
    private final ResourceLocation id;

    public StoveTypeCraftingType(NonNullList<Ingredient> inputlist, ItemStack output, ResourceLocation id) {
        this.inputlist = inputlist;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide){
            return false;
        }
        return inputlist.get(0).test(simpleContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<StoveTypeCraftingType>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "stove_top_crafting_type";
    }

    public static class Serializer implements RecipeSerializer<StoveTypeCraftingType>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MercysUtils.MOD_ID, "stove_top_crafting_type");

        @Override
        public StoveTypeCraftingType fromJson(ResourceLocation recipeID, JsonObject serializedRecipeObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipeObject, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(serializedRecipeObject, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int num = 0; num < inputs.size(); num++){
                inputs.set(num, Ingredient.fromJson(ingredients.get(num)));
            }

            return new StoveTypeCraftingType(inputs, output, recipeID);
        }

        @Override
        public @Nullable StoveTypeCraftingType fromNetwork(ResourceLocation recipeId, FriendlyByteBuf byteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(byteBuf.readInt(), Ingredient.EMPTY);

            for (int num = 0; num < inputs.size(); num++){
                inputs.set(num, Ingredient.fromNetwork(byteBuf));
            }

            ItemStack output = byteBuf.readItem();

            return new StoveTypeCraftingType(inputs, output, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf byteBuf, StoveTypeCraftingType tutorialCraftingVar) {
            byteBuf.writeInt(tutorialCraftingVar.inputlist.size());

            for (Ingredient ingredient : tutorialCraftingVar.getIngredients()){
                ingredient.toNetwork(byteBuf);
            }

            byteBuf.writeItemStack(tutorialCraftingVar.getResultItem(null), false);
        }
    }
}
