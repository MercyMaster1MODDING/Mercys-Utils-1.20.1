package com.mercysUtils.library.Commands;

import com.mercysUtils.library.Titles.PlayerTitlesProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;


public class TitleDebugCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("givemytitle")
                .then(Commands.argument("title", StringArgumentType.word())
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            String titleName = StringArgumentType.getString(context, "title");

                            player.getCapability(PlayerTitlesProvider.PLAYER_TITLES).ifPresent(titles -> {
                                titles.addTitle(titleName);
                                titles.setActiveTitle(titleName);
                            });

                            context.getSource().sendSuccess(
                                    () -> Component.literal("Title '" + titleName + "' given and activated!"),
                                    false
                            );

                            return 1;
                        })
                )
        );

    }
}
