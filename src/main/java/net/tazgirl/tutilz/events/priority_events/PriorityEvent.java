package net.tazgirl.tutilz.events.priority_events;

import net.neoforged.bus.api.Event;
import net.tazgirl.tutilz.events.EventPriorityPair;

import java.util.ArrayList;
import java.util.List;

public abstract class PriorityEvent<T extends EventAccessor<?>> extends Event
{

    protected List<EventPriorityPair<T>> eventPriorityPairs = new ArrayList<>();

    public void addEventPair(EventPriorityPair<T> pair)
    {
        eventPriorityPairs.add(pair);
    }

    protected List<EventPriorityPair<T>> getPairs()
    {
        return eventPriorityPairs;
    }

}
