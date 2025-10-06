package net.tazgirl.tutilz.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.function.Predicate;

import static net.minecraft.commands.Commands.argument;

@ParametersAreNonnullByDefault
public class QuickRegisterCommand
{
    public static void NoArg(CommandDispatcher<CommandSourceStack> dispatcher, String command, int permission, Predicate<CommandSourceStack> commandFunction)
    {
        dispatcher.register(Commands.literal(command)
                .requires(source -> source.hasPermission(permission))
                        .executes(context ->
                        {
                            if(commandFunction.test(context.getSource()))
                            {
                                return 1;
                            }
                            else
                            {
                                return 0;
                            }
                        })
                );
    }

    public static void BuildThreeStageArgTree(CommandDispatcher<CommandSourceStack> dispatcher, String baseCommand, int permission, Map<String, ArgPair[]> branches)
    {
        LiteralArgumentBuilder<CommandSourceStack> baseRoot = Commands.literal(baseCommand);


        baseRoot.requires(source -> source.hasPermission(permission));


        for(Map.Entry<String, ArgPair[]> branch : branches.entrySet())
        {
            baseRoot.then(BuildSecondaryBranch(branch.getKey(), branch.getValue()));
        }

        dispatcher.register(baseRoot);

    }





    private static LiteralArgumentBuilder<CommandSourceStack> BuildSecondaryBranch(String nameOfRoot, ArgPair... arguments)
    {
        if(arguments.length == 0){return null;}

        LiteralArgumentBuilder<CommandSourceStack> argHolder = Commands.literal(nameOfRoot);

        for(ArgPair pair: arguments)
        {
            argHolder.then(Commands.literal(pair.argument()).executes(context ->
            {
                if(pair.function().test(context.getSource()))
                {
                    return 1;
                }

                return 0;
            }));
        }

        return argHolder;
    }

}


