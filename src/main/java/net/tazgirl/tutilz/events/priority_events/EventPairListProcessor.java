package net.tazgirl.tutilz.events.priority_events;

import net.neoforged.bus.api.Event;
import net.tazgirl.tutilz.events.EventPriorityPair;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ParametersAreNonnullByDefault
public class EventPairListProcessor
{

    public static <T extends EventAccessor<?>> T ResolveAndRun(List<EventPriorityPair<T>> pairs, T accessor)
    {
        pairs.sort(Comparator.comparingInt(EventPriorityPair::priority));

        for(int i = 0; i < pairs.size();)
        {

            List<EventPriorityPair<T>> equalPairsAlphabetical = EventPairListProcessor.AlphabetisePriorityConflicts(pairs, i);

            i += equalPairsAlphabetical.size();

            equalPairsAlphabetical.sort(Comparator.comparing(eventPair -> eventPair.operation().toString(), String::compareToIgnoreCase));

            for(EventPriorityPair<T> pair: equalPairsAlphabetical)
            {
                pair.operation().accept(accessor);
            }
        }

        return accessor;
    }

    public static <P extends Event,T extends EventAccessor<?>> List<EventPriorityPair<T>> AlphabetisePriorityConflicts(List<EventPriorityPair<T>> fullList, int currentI)
    {
        List<EventPriorityPair<T>> returnList = new ArrayList<>();

        returnList.add(fullList.get(currentI));


        int offset = 1;
        EventPriorityPair<T> checkPair;
        while(true)
        {
            if(currentI + offset >= fullList.size()){break;}

            checkPair = fullList.get(currentI + offset);

            if(checkPair.priority() == returnList.getFirst().priority())
            {
                returnList.add(checkPair);
            }
            else
            {
                break;
            }

            offset++;
        }

        returnList.sort(Comparator.comparing(eventPair -> eventPair.operation().toString(), String.CASE_INSENSITIVE_ORDER));

        return returnList;

    }
}
