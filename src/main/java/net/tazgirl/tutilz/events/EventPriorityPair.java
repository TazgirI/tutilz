package net.tazgirl.tutilz.events;

import net.tazgirl.tutilz.NullThrow;
import net.tazgirl.tutilz.events.event_priority_overrides.EventAccessor;

import java.util.function.Consumer;

public record EventPriorityPair<T extends EventAccessor>(int priority, Consumer<T> operation)
{
    // REMEMBER: Lower priority's fire FIRST

    // The context of where you want your event to fire in the priority list will change based on the event and what other listeners are doing
    // Check the EventAccesor for your specific event to find notes of rough brackets for where certain things should be performed
    // (this matters most for events focused around amounts such as damage or knockback amounts)

    public EventPriorityPair(int priority, Consumer<T> operation)
    {
        NullThrow.Check(priority, operation);

        this.priority = priority;
        this.operation = operation;
    }

}
