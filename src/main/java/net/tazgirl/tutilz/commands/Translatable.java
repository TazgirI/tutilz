package net.tazgirl.tutilz.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Translatable
{
    public static void Success(CommandSourceStack source, String location)
    {
        source.sendSuccess(() -> Component.translatable(location),true);
    }

    public static void Success(CommandSourceStack source, String location, Object... args)
    {
        source.sendSuccess(() -> Component.translatable(location, args),true);
    }

}
