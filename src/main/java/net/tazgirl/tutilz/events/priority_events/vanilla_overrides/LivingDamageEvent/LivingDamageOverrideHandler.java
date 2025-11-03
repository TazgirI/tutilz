package net.tazgirl.tutilz.events.priority_events.vanilla_overrides.LivingDamageEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.tutilz.events.EventPriorityPair;
import net.tazgirl.tutilz.events.priority_events.EventPairListProcessor;

import java.util.List;

public class LivingDamageOverrideHandler<T extends LivingDamageOverrideAccessor> extends LivingDamageOverride<T>
{

    @SubscribeEvent
    public static void OnLivingDamagePre(LivingDamageEvent.Pre event)
    {
        LivingDamageOverrideAccessor.Pre accessor = ProcessPreEvent(event, LivingDamageOverride.FirePre());

        event.setNewDamage(accessor.finalDamage);

    }

    @SubscribeEvent
    public static void OnLivingDamagePost(LivingDamageEvent.Post event)
    {
        ProcessPostEvent(event, LivingDamageOverride.FirePost());
    }

    private static LivingDamageOverrideAccessor.Pre ProcessPreEvent(LivingDamageEvent.Pre originalEvent, LivingDamageOverride.OverridePre priorityEvent)
    {
        List<EventPriorityPair<LivingDamageOverrideAccessor.Pre>> pairs = priorityEvent.getPairs();

        LivingDamageOverrideAccessor.Pre eventAccessor = new LivingDamageOverrideAccessor.Pre(originalEvent, originalEvent.getOriginalDamage(), originalEvent.getNewDamage());

        return EventPairListProcessor.ResolveAndRun(pairs, eventAccessor);
    }

    private static LivingDamageOverrideAccessor.Post ProcessPostEvent(LivingDamageEvent.Post originalEvent, LivingDamageOverride.OverridePost priorityEvent)
    {
        List<EventPriorityPair<LivingDamageOverrideAccessor.Post>> pairs = priorityEvent.getPairs();

        LivingDamageOverrideAccessor.Post eventAccessor = new LivingDamageOverrideAccessor.Post(originalEvent, originalEvent.getOriginalDamage(), originalEvent.getNewDamage());

        return EventPairListProcessor.ResolveAndRun(pairs, eventAccessor);
    }


}
