package com.ksuzuki.hunter.handler;

import com.ksuzuki.hunter.reference.Reference;
import com.ksuzuki.hunter.utility.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by suzuki on 8/9/14.
 */
public class ConfigurationHandler
{

    public static Configuration configuration;
    public static boolean testValue = false;


    public static void init(File configFile)
    {
        if (configuration == null) // create the configuration object from the given configuration file
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
            System.out.println("New config loaded.");
        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            // resync the configs
            loadConfiguration();
            LogHelper.info("Config changed.");
        }
    }

    private static void loadConfiguration()
    {
        testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is an example configuration value.");
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

}
