package net.tazgirl.tutilz.events.priority_events.vanilla_overrides.LivingDamageEvent;

import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.tutilz.events.EventPriorityPair;
import net.tazgirl.tutilz.events.priority_events.PriorityEvent;

import java.util.List;

public abstract class LivingDamageOverride<T extends LivingDamageOverrideAccessor> extends PriorityEvent<T>
{

    public static class OverridePre extends LivingDamageOverride<LivingDamageOverrideAccessor.Pre>
    {

    }

    public static class OverridePost extends LivingDamageOverride<LivingDamageOverrideAccessor.Post>
    {

    }

    public List<EventPriorityPair<T>> getPairs()
    {
        return super.getPairs();
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
