package net.tazgirl.tutilz.chat;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.tazgirl.tutilz.Globals;
import net.tazgirl.tutilz.NullThrow;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class SendMessage
{

    public static boolean All(String message)
    {
        return All(Component.literal(message));
    }

    public static boolean All(Component message)
    {
        NullThrow.Check(message);

        MinecraftServer server = Globals.server;
        List<ServerPlayer> players;

        if (server != null && !(players = server.getPlayerList().getPlayers()).isEmpty())
        {
            for (ServerPlayer player : players)
            {
                player.sendSystemMessage(message);
            }
            return true;
        }

        return false;
    }



    public static void Specific(String message,ServerPlayer player)
    {
        Specific(Component.literal(message), player);
    }

    public static void Specific(Component message,ServerPlayer player)
    {
        NullThrow.Check(message, player);

        player.sendSystemMessage(message);
    }

}
