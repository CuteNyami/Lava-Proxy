package me.cutenyami.lava;

import me.cutenyami.lava.command.impl.HelpCMD;
import me.cutenyami.lava.command.impl.StopCMD;
import me.cutenyami.lava.console.Console;
import me.cutenyami.lava.console.listener.IConsoleListener;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Lava.init();
            Console console = new Console();

            console.addListener(new IConsoleListener() {
                @Override
                public void handleInit(Console console) {
                    console.sendMessage("§cThis is a beta build!", "§cThis build is under development and may have bugs!");
                    Lava.getInstance().getCommandManager().register(new HelpCMD(), new StopCMD(console));
                }

                @Override
                public void handleInput(Console console, String line) {

                }

                @Override
                public void handleClose(Console console) {

                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
