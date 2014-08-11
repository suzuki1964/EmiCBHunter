package com.ksuzuki.hunter.client.gui;

import com.ksuzuki.hunter.handler.ConfigurationHandler;
import com.ksuzuki.hunter.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by suzuki on 8/11/14.
 */
public class ModGuiConfig extends GuiConfig
{
    public ModGuiConfig(GuiScreen guiScreen)
    {
        super(guiScreen,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                false, /* doesn't require world restart */
                false, /* doesn't require Minecraft restart */
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}

