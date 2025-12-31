package net.tazgirl.tutilz.registers;

import net.minecraft.resources.ResourceLocation;

public class MakeRegistryAddress
{
    public static String quick(ResourceLocation location)
    {
        return quick(location.toString());
    }

    public static String quick(String location)
    {
        int colon = location.indexOf(':');
        int lastSlash = location.lastIndexOf('/');
        String namespace = location.substring(0, colon + 1);
        String file = location.substring(lastSlash != -1 ? lastSlash + 1 : colon + 1, location.lastIndexOf('.'));
        return namespace + file;
    }

    public static String withPath(ResourceLocation location, String path)
    {
        return withPath(location.toString(), path);
    }

    public static String withPath(String location, String path)
    {
        int colon = location.indexOf(':');
        int pathIndex = location.indexOf(path);
        int trailingSlash = path.endsWith("/") ? 0 : 1;
        String namespace = location.substring(0, colon + 1);
        String additionalPath = location.substring(pathIndex + path.length() + trailingSlash, location.lastIndexOf('.'));
        return namespace + additionalPath;
    }
}
