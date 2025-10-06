package net.tazgirl.tutilz.commands;

import net.minecraft.commands.CommandSourceStack;

import java.util.function.Predicate;

public record ArgPair(String argument, Predicate<CommandSourceStack> function)
{

    public String getArgument()
    {
        return this.argument;
    }

    public Predicate<CommandSourceStack> getFunction()
    {
        return this.function;
    }

    public static ArgPair New(String newString, Predicate<CommandSourceStack> newPredicate)
    {
        return new ArgPair(newString, newPredicate);
    }
}
