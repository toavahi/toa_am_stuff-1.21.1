package net.toavahi.toa_am_stuff.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.toavahi.toa_am_stuff.ToaAmethystStuff;

public class ModSounds {
    public static final SoundEvent AM_COPPER_BLOCK_BREAK = registerSoundEvent("am_copper_block_break");
    public static final SoundEvent AM_COPPER_BLOCK_STEP = registerSoundEvent("am_copper_block_step");
    public static final SoundEvent AM_COPPER_BLOCK_PLACE = registerSoundEvent("am_copper_block_place");
    public static final SoundEvent AM_COPPER_BLOCK_FALL = registerSoundEvent("am_copper_block_fall");

    public static final BlockSoundGroup AM_COPPER_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f,
            AM_COPPER_BLOCK_BREAK, AM_COPPER_BLOCK_STEP, AM_COPPER_BLOCK_PLACE, AM_COPPER_BLOCK_FALL, AM_COPPER_BLOCK_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(ToaAmethystStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
