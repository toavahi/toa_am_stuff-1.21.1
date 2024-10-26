package net.toavahi.toa_am_stuff.util;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

public class ModDataComponents {
    public static final ComponentType<Boolean> AM_CHISEL = register("am_chisel_am", ComponentType.<Boolean>builder().codec(Codec.BOOL).packetCodec(PacketCodecs.BOOL).build());
    public static final ComponentType<Boolean> POISON_FOOD = register("am_food", ComponentType.<Boolean>builder().codec(Codec.BOOL).packetCodec(PacketCodecs.BOOL).build());
    public static final ComponentType<Float> COLOR = register("color", ComponentType.<Float>builder().codec(Codec.FLOAT).packetCodec(PacketCodecs.FLOAT).build());

    private static <T> ComponentType<T> register(String name, ComponentType<T> builder){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ToaAmethystStuff.MOD_ID, name), builder);
    }

    public static void registerComponents(){}
}
