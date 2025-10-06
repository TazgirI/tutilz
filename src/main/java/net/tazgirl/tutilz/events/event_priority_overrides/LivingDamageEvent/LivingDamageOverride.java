package net.tazgirl.tutilz.events.event_priority_overrides.LivingDamageEvent;

import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.tutilz.events.EventPriorityPair;

import java.util.ArrayList;
import java.util.List;

public abstract class LivingDamageOverride<T extends LivingDamageEventAccessor> extends Event
{
    boolean damageCancelFlag = false;
    boolean fullCancelFlag = false;




    public static class OverridePre extends LivingDamageOverride<LivingDamageEventAccessor.Pre>
    {
        private List<EventPriorityPair<LivingDamageEventAccessor.Pre>> methods = new ArrayList<>();

        public void AddEventPair(EventPriorityPair<LivingDamageEventAccessor.Pre> newEventPriorityPair)
        {
            methods.add(newEventPriorityPair);
        }

        protected List<EventPriorityPair<LivingDamageEventAccessor.Pre>> GetEventPairs()
        {
            return methods;
        }
    }

    public static class OverridePost extends LivingDamageOverride<LivingDamageEventAccessor.Post>
    {
        private List<EventPriorityPair<LivingDamageEventAccessor.Post>> methods = new ArrayList<>();

        public void AddEventPair(EventPriorityPair<LivingDamageEventAccessor.Post> newEventPriorityPair)
        {
            methods.add(newEventPriorityPair);
        }

        protected List<EventPriorityPair<LivingDamageEventAccessor.Post>> GetEventPairs()
        {
            return methods;
        }
    }

    public static OverridePre FirePre()
    {
        return NeoForge.EVENT_BUS.post(new OverridePre());
    }

    public static OverridePost FirePost()
    {
        return NeoForge.EVENT_BUS.post(new OverridePost());
    }

}
