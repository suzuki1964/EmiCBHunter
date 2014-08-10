package com.ksuzuki.hunter.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by suzuki on 8/9/14.
 */
public class ConfigurationHandler
{
    public static void init(File configFile)
    {
        // create the configuration object from the given configuration file
        Configuration configuration = new Configuration(configFile);
        boolean configValue = false;

        try
        {
            configuration.load(); // try to load the file

            // read in properties from the configuration file
            configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value.").getBoolean(true);
        }
        catch (Exception e)
        {
            // log the exception
        }
        finally
        {
            configuration.save(); // save the configuration file
        }

        System.out.println("The config value is " + configValue);
    }
}
