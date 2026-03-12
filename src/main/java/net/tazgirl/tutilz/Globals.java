package net.tazgirl.tutilz;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid = Tutilz.MODID)
public class Globals
{
    public static MinecraftServer server = null;


    @SubscribeEvent
    public static void OnServerStarting(ServerStartedEvent event)
    {
        server = event.getServer();

    }

}
