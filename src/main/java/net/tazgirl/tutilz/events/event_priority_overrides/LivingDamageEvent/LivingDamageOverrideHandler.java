package net.tazgirl.tutilz.events.event_priority_overrides.LivingDamageEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.tutilz.events.EventPriorityPair;
import net.tazgirl.tutilz.events.event_priority_overrides.EventAccessor;
import org.apache.logging.log4j.util.PropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LivingDamageOverrideHandler
{

    @SubscribeEvent
    public static void OnLivingDamagePre(LivingDamageEvent.Pre event)
    {
        ProcessPreEvent(event, LivingDamageOverride.FirePre());
    }


    @SubscribeEvent
    public static void OnLivingDamagePost(LivingDamageEvent.Post event)
    {
        ProcessPostEvent(event, LivingDamageOverride.FirePost());
    }

    private static void ProcessPostEvent(LivingDamageEvent.Post originalEvent, LivingDamageOverride.OverridePost priorityEvent)
    {
        List<EventPriorityPair<LivingDamageEventAccessor.Post>> sortedMethods = priorityEvent.GetEventPairs();
        sortedMethods.sort(Comparator.comparingInt(EventPriorityPair::priority));


        LivingDamageEventAccessor.Post eventAccessor = new LivingDamageEventAccessor.Post(originalEvent, originalEvent.getOriginalDamage());



        for(int i = 0; i < sortedMethods.size(); i++)
        {
            List<EventPriorityPair<LivingDamageEventAccessor.Post>> equalMethodsAlphabetical = new ArrayList<>();

            equalMethodsAlphabetical.add(sortedMethods.get(i));

            int offset = 1;
            EventPriorityPair<LivingDamageEventAccessor.Post> checkPair;
            while(true)
            {
                checkPair = sortedMethods.get(i + offset);

                if(checkPair.priority() == equalMethodsAlphabetical.getFirst().priority())
                {
                    equalMethodsAlphabetical.add(checkPair);
                }
                else
                {
                    i += offset - 1;
                    break;
                }

                offset++;
            }

            equalMethodsAlphabetical.sort(Comparator.comparing(eventPair -> eventPair.operation().toString(), String::compareToIgnoreCase));

            for(EventPriorityPair<LivingDamageEventAccessor.Post> pair: equalMethodsAlphabetical)
            {
                pair.operation().accept(eventAccessor);
            }

        }

    }



}
