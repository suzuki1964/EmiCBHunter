package com.ksuzuki.hunter.init;

import com.ksuzuki.hunter.item.ItemH;
import com.ksuzuki.hunter.item.ItemPoisonArrow;
import com.ksuzuki.hunter.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by suzuki on 8/15/14.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemH poisonArrow = new ItemPoisonArrow();

    public static void init()
    {
        GameRegistry.registerItem(poisonArrow, "poisonArrow");
    }
}
