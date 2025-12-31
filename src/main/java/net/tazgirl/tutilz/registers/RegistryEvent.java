package net.tazgirl.tutilz.registers;

import net.neoforged.bus.api.Event;
import net.tazgirl.tutilz.admin.Logging;

import java.util.Map;

public abstract class RegistryEvent<T> extends Event
{
    Map<String, T> registry;

    public RegistryEvent(Map<String, T> registry)
    {
        this.registry = registry;
    }

    public boolean put(String namespace, String path, T value)
    {
        String address = namespace + ":" + path;
        if(!registry.containsKey(address))
        {
            if(!registry.containsValue(value))
            {
                registry.put(address, value);
                return true;
            }
            Logging.Debug("Attempted to put registry value but found the value \"" + value + "\" was a duplicate. Registry Event: " + this.getClass(), null);
            return false;
        }
        Logging.Debug("Attempted to put registry value but found the key \"" + address + "\" was a duplicate. Registry Event: " + this.getClass(), null);
        return false;
    }

}
