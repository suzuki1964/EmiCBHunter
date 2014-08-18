package com.ksuzuki.hunter.init;

import com.ksuzuki.hunter.block.BlockFoliage;
import com.ksuzuki.hunter.block.BlockH;
import com.ksuzuki.hunter.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by suzuki on 8/16/14.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockH foliage = new BlockFoliage();

    public static void init()
    {
        GameRegistry.registerBlock(foliage, "foliage");
    }
}
