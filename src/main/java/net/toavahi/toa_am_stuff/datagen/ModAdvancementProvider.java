package net.toavahi.toa_am_stuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        ModItems.AM_DUST,
                        Text.translatable("advancement.toa_am_stuff.root.title"),
                        Text.translatable("advancement.toa_am_stuff.root.desc"),
                        Identifier.of("textures/block/amethyst_block.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("shard", InventoryChangedCriterion.Conditions.items(Items.AMETHYST_SHARD))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/root");
        Advancement.Builder.create()
                .parent(root)
                .display(
                        ModItems.AM_CHISEL,
                        Text.translatable("advancement.toa_am_stuff.chisel.title"),
                        Text.translatable("advancement.toa_am_stuff.chisel.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("chisel", InventoryChangedCriterion.Conditions.items(ModItems.AM_CHISEL))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/chisel");
        AdvancementEntry dust = Advancement.Builder.create()
                .parent(root)
                .display(
                        ModItems.AM_DUST,
                        Text.translatable("advancement.toa_am_stuff.dust.title"),
                        Text.translatable("advancement.toa_am_stuff.dust.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("dust", InventoryChangedCriterion.Conditions.items(ModItems.AM_DUST))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/dust");
        Advancement.Builder.create()
                .parent(dust)
                .display(
                        ModItems.AM_GRENADE,
                        Text.translatable("advancement.toa_am_stuff.grenade.title"),
                        Text.translatable("advancement.toa_am_stuff.grenade.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("grenade", InventoryChangedCriterion.Conditions.items(ModItems.AM_GRENADE))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/grenade");
        Advancement.Builder.create()
                .parent(root)
                .display(
                        ModItems.AM_BRUSH,
                        Text.translatable("advancement.toa_am_stuff.brush.title"),
                        Text.translatable("advancement.toa_am_stuff.brush.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("brush", InventoryChangedCriterion.Conditions.items(ModItems.AM_BRUSH))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/brush");
        Advancement.Builder.create()
                .parent(dust)
                .display(
                        ModItems.AM_COPPER_INGOT,
                        Text.translatable("advancement.toa_am_stuff.ingot.title"),
                        Text.translatable("advancement.toa_am_stuff.ingot.desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("ingot", InventoryChangedCriterion.Conditions.items(ModItems.AM_COPPER_INGOT))
                .build(consumer, ToaAmethystStuff.MOD_ID + "/ingot");
    }
}
