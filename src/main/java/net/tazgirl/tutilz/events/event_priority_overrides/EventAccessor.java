package net.tazgirl.tutilz.events.event_priority_overrides;

import net.neoforged.bus.api.Event;

public class EventAccessor<T extends Event>
{
    protected T mySource;

    public EventAccessor(T mySource)
    {
        this.mySource = mySource;
    }


}
