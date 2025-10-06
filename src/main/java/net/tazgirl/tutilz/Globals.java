package net.tazgirl.tutilz;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tazgirl.tutilz.chat.SendMessage;

import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EventBusSubscriber(modid = Tutilz.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Globals
{
    public static MinecraftServer server = null;

    @SubscribeEvent
    public static void OnServerStarting(ServerStartedEvent event)
    {
        server = event.getServer();

    }

}
