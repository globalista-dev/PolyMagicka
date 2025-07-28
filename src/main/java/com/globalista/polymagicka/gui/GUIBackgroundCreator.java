package com.globalista.polymagicka.gui;

import com.globalista.polymagicka.util.Helper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;


public class GUIBackgroundCreator {
    private static final List<FontTexture> FONT_TEXTURES = new ArrayList<>();
    private static char character = 'a'; // start from a safe printable range

    // Your GUI font style
    public static final Style STYLE = Style.EMPTY.withColor(0xFFFFFF)
            .withFont(Helper.id("gui"));

    /**
     * Registers a new background texture using a font glyph.
     * The PNG must be located at textures/sgui/{path}.png
     */
    public static Function<Text, Text> background(String path) {
        char c = character++;

        FONT_TEXTURES.add(new FontTexture(
                Helper.id("sgui/" + path),
                13, // ascent (position)
                256, // height (typical full character block)
                new char[][]{{c}}
        ));
        return new TextBuilder(Text.literal(String.valueOf(c)).setStyle(STYLE));
    }

    /**
     * Call this inside your mod initializer to register assets.
     */
    public static void init() {
        PolymerResourcePackUtils.RESOURCE_PACK_CREATION_EVENT.register((b) ->
                GUIBackgroundCreator.generateAssets(b::addData));
    }

    /**
     * Generates font JSON and maps font glyphs to PNGs.
     */
    public static void generateAssets(BiConsumer<String, byte[]> assetWriter) {
        JsonObject fontJson = new JsonObject();
        JsonArray providers = new JsonArray();

        for (FontTexture entry : FONT_TEXTURES) {
            JsonObject bitmap = new JsonObject();
            bitmap.addProperty("type", "bitmap");
            bitmap.addProperty("file", entry.path().toString() + ".png");
            bitmap.addProperty("ascent", entry.ascent());
            bitmap.addProperty("height", entry.height());

            JsonArray chars = new JsonArray();
            for (char[] line : entry.chars()) {
                chars.add(new String(line));
            }

            bitmap.add("chars", chars);
            providers.add(bitmap);
        }

        fontJson.add("providers", providers);

        assetWriter.accept("assets/yourmodid/font/gui.json", fontJson.toString().getBytes(StandardCharsets.UTF_8));
    }

    // Used internally to attach the glyph to a Text object
    private record TextBuilder(Text base) implements Function<Text, Text> {
        @Override
        public Text apply(Text text) {
            return Text.empty().append(base).append(text);
        }
    }

    // Holds font glyph registration info
    private record FontTexture(Identifier path, int ascent, int height, char[][] chars) {}

    public static Function<Text, Text> chestBackground(String path) {
        char c = character++;
        FONT_TEXTURES.add(new FontTexture(
                Helper.id( "sgui/" + path),
                -4, // fine-tune as needed
                256,
                new char[][]{{c}}
        ));
        return new TextBuilder(Text.literal(String.valueOf(c)).setStyle(STYLE));
    }
}
