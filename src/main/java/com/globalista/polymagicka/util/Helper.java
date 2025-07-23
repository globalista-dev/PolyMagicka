package com.globalista.polymagicka.util;

import com.globalista.polymagicka.PolyMagicka;
import net.minecraft.util.Identifier;

public class Helper {

    public static Identifier id(String name){
        return Identifier.of(PolyMagicka.MOD_ID, name);
    }

}
