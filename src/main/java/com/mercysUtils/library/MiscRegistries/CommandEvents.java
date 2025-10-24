package com.mercysUtils.library.MiscRegistries;

import com.mercysUtils.library.Commands.TitleDebugCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mercysutils")
public class CommandEvents {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        TitleDebugCommand.register(event.getDispatcher());
    }
}
