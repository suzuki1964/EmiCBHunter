package com.ksuzuki.hunter.creativetab;

import com.ksuzuki.hunter.init.ModItems;
import com.ksuzuki.hunter.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by suzuki on 8/17/14.
 */
public class CreativeTabH
{
    public static final CreativeTabs H_tab = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.poisonArrow;
        }

    };
}
