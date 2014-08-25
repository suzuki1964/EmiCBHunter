package com.ksuzuki.hunter.init;

import com.ksuzuki.hunter.item.ItemBowH;
import com.ksuzuki.hunter.item.ItemH;
import com.ksuzuki.hunter.item.ItemPoisonArrow;
import com.ksuzuki.hunter.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBow;

/**
 * Created by suzuki on 8/15/14.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemH poisonArrow = new ItemPoisonArrow();
    public static final ItemBow customBow = new ItemBowH();

    public static void init()
    {
        GameRegistry.registerItem(poisonArrow, "poisonArrow");
        GameRegistry.registerItem(customBow, "customBow");
    }
}
