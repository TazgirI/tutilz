package net.tazgirl.tutilz.events.priority_events;

import net.neoforged.bus.api.Event;

import javax.annotation.Nullable;

public abstract class EventAccessor<T extends EventAccessor<?>> extends PriorityEvent<T>
{
    protected Event overridenEvent;

    public EventAccessor(@Nullable Event overridenEvent)
    {

    }



}
