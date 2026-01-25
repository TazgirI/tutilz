package net.tazgirl.tutilz;

public class Task
{
    public Runnable runnable;
    public int tickToCall;

    public Task(Runnable runnable, int ticks)
    {
        if(Globals.server == null)
        {
            return;
        }

        this.runnable = runnable;
        this.tickToCall = Globals.server.getTickCount() + ticks;
    }
}
