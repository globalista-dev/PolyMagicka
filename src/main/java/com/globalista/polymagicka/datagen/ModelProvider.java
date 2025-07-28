package com.globalista.polymagicka.datagen;

import com.globalista.polymagicka.item.BoundItems;
import com.globalista.polymagicka.util.Helper;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator b) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator i) {

        for (Item item : BoundItems.BOUND_ITEMS) {
            if (item == BoundItems.BOUND_CROSSBOW) {
                i.registerCrossbow(BoundItems.BOUND_CROSSBOW);
            } else if (item == BoundItems.BOUND_BOW) {
                i.registerBow(BoundItems.BOUND_BOW);
            }
            else {
                i.register(item, Models.GENERATED);
            }
        }




    }
}
