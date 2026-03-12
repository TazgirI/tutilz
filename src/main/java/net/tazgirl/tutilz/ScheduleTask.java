package net.tazgirl.tutilz;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.apache.logging.log4j.core.net.Priority;

import java.util.Comparator;
import java.util.PriorityQueue;

@EventBusSubscriber
public class ScheduleTask
{
    public final static PriorityQueue<Task> preTasks = new PriorityQueue<>(Comparator.comparingInt(task -> task.tickToCall));
    public final static PriorityQueue<Task> postTasks = new PriorityQueue<>(Comparator.comparingInt(task -> task.tickToCall));


    @SubscribeEvent
    public static void ServerTickEvent(ServerTickEvent.Pre event)
    {
        int currentTick = event.getServer().getTickCount();

        while (!preTasks.isEmpty() && preTasks.peek().tickToCall <= currentTick)
        {
            Task task = preTasks.poll();
            task.runnable.run();
        }

    }

    @SubscribeEvent
    public static void ServerTickEvent(ServerTickEvent.Post event)
    {
        int currentTick = event.getServer().getTickCount();

        while (!postTasks.isEmpty() && postTasks.peek().tickToCall <= currentTick)
        {
            Task task = postTasks.poll();
            task.runnable.run();
        }
    }

    public static void scheduleTask(Runnable runnable, int ticks, Priority priority)
    {
        if(priority.equals(Priority.PRE))
        {
            preTasks.add(new Task(runnable, ticks));
        }
        else
        {
            postTasks.add(new Task(runnable, ticks));
        }
    }

    public enum Priority
    {
        PRE,
        POST;
    }
}
