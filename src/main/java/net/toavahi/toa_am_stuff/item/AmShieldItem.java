package net.toavahi.toa_am_stuff.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;

public class AmShieldItem extends ShieldItem {
    public AmShieldItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem() == Items.AMETHYST_SHARD || super.canRepair(stack, ingredient);
    }
}
