package com.ksuzuki.hunter;

import com.ksuzuki.hunter.configuration.ConfigurationHandler;
import com.ksuzuki.hunter.proxy.IProxy;
import com.ksuzuki.hunter.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by suzuki on 8/2/14.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class Hunter
{
    @Mod.Instance(Reference.MOD_ID)
    public static Hunter instance;

    @SidedProxy (clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {

    }
}
