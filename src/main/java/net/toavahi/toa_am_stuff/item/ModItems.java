package net.toavahi.toa_am_stuff.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;
import net.toavahi.toa_am_stuff.block.ModBlocks;
import net.toavahi.toa_am_stuff.util.ModDataComponents;

public class ModItems {
    public static final Item AM_TRIANGLE = registerItem("am_triangle", new AmTrItem(new Item.Settings().maxCount(1)));
    public static final Item AM_CHISEL = registerItem("am_chisel", new AmChiselItem(new Item.Settings().maxCount(1).component(ModDataComponents.AM_CHISEL, false)));
    public static final Item AM_SHIELD = registerItem("am_shield", new AmShieldItem(new Item.Settings().maxDamage(672)));
    public static final Item AM_GRENADE = registerItem("am_grenade", new AmGrenadeItem(new Item.Settings().maxCount(16)));
    public static final Item AM_BRUSH = registerItem("am_brush", new AmBrushItem(new Item.Settings().maxCount(1).maxDamage(64)
            .component(ModDataComponents.COLOR, 0F)));
    public static final Item AM_DUST = registerItem("am_dust", new Item(new Item.Settings()));
    public static final Item AM_COPPER_INGOT = registerItem("am_copper_ingot", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ToaAmethystStuff.MOD_ID, name), item);
    }

    private static void addItemToCombatTab(FabricItemGroupEntries entries) {
        entries.add(AM_SHIELD);
        entries.add(AM_GRENADE);
    }

    private static void addItemToIngredientTab(FabricItemGroupEntries entries){
        entries.addAfter(Items.PHANTOM_MEMBRANE, AM_DUST);
    }

    private static void addItemToToolTab(FabricItemGroupEntries entries){
        entries.add(AM_TRIANGLE);
        entries.add(AM_CHISEL);
        entries.add(AM_BRUSH);
    }

    private static void addItemToRedstoneTab(FabricItemGroupEntries entries){

    }

    private static void addItemToNatureTab(FabricItemGroupEntries entries){

    }

    private static void addItemToFoodTab(FabricItemGroupEntries entries){

    }

    private static void addItemToFunctionalTab(FabricItemGroupEntries entries){

    }

    private static void addItemToSpawnEggTab(FabricItemGroupEntries entries){

    }

    private static void addItemToBuildingTab(FabricItemGroupEntries entries){
        entries.addAfter(Blocks.WAXED_OXIDIZED_COPPER_BULB, ModBlocks.AM_COPPER_BLOCK);
        entries.addAfter(ModBlocks.AM_COPPER_BLOCK, ModBlocks.AM_COPPER_STAIRS);
        entries.addAfter(ModBlocks.AM_COPPER_STAIRS, ModBlocks.AM_COPPER_SLAB);
        entries.addAfter(ModBlocks.AM_COPPER_SLAB, ModBlocks.AM_COPPER_WALL);
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemToCombatTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIngredientTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemToToolTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModItems::addItemToRedstoneTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemToNatureTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemToFoodTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addItemToFunctionalTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemToSpawnEggTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemToBuildingTab);
    }
}
