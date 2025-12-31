package net.tazgirl.tutilz.registers;

import java.util.*;

// Unusable, just exists to copy over boilerplate
public class RegisterCopyBase
{
    static final Map<String, Object> register = new HashMap<>();

    public static Object get(String address)
    {
        return register.get(address);
    }

    public static Object put(String address, Object value)
    {
        return register.put(address, value);
    }

    public static List<Object> putAll(Map<String, Object> map)
    {
        List<Object> returnList = new ArrayList<>();

        map.forEach((key, value) -> returnList.add(put(key, value)));

        return returnList;
    }


    public static List<Object> getList(List<String> addresses)
    {
        List<Object> returnList = new ArrayList<>();

        addresses.forEach(string -> returnList.add(get(string)));


        return returnList.stream().filter(Objects::nonNull).toList();
    }



    public static boolean hasAddress(String address)
    {
        return register.containsKey(address);
    }

    public static boolean hasObject(Object value)
    {
        return register.containsValue(value);
    }

    public static String addressFromObject(Object value)
    {
        if(!hasObject(value))
        {
            return null;
        }

        for(Map.Entry<String, Object> entry : register.entrySet())
        {
            if(entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }

        return null;
    }

    public static String registerString()
    {
        return register.toString();
    }
}
