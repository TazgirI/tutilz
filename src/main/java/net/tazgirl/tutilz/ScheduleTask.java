package net.tazgirl.tutilz;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@EventBusSubscriber
public class ScheduleTask
{
    public final static PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(task -> task.tickToCall));

    @SubscribeEvent
    public static void ServerTickEvent(ServerTickEvent.Pre event)
    {
        int currentTick = event.getServer().getTickCount();

        while (!tasks.isEmpty() && tasks.peek().tickToCall <= currentTick)
        {
            Task task = tasks.poll();
            task.runnable.run();
        }

    }

    public static void scheduleTask(Runnable runnable, int ticks)
    {
        tasks.add(new Task(runnable, ticks));
    }
}
