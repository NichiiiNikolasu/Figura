package org.figuramc.figura.mixin.font;

import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(targets = "net.minecraft.client.gui.font.FontSet$SelectedGlyphs")
public interface FontSet$SelectedGlyphsAccessor {

    @Accessor("any")
    Supplier<BakedGlyph> figura$getAny();

    @Accessor("nonFishy")
    Supplier<BakedGlyph> figura$getNonFishy();
}
