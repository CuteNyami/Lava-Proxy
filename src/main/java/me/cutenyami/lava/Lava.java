package me.cutenyami.lava;

import me.cutenyami.lava.command.impl.HelpCMD;
import me.cutenyami.lava.command.impl.StopCMD;
import me.cutenyami.lava.console.Console;
import me.cutenyami.lava.console.listener.IConsoleListener;

import java.io.IOException;

public class Lava {

    public static final String NAME = "Lava-Proxy";

    public static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        try {
            ProxyServer.init();
            Console console = new Console();

            console.addListener(new IConsoleListener() {
                @Override
                public void handleInit(Console console) {
                    console.sendMessage("§4This is a dev build!", "§4This build is under development and may have bugs!");

                    ProxyServer.getInstance().getCommandManager().register(new HelpCMD(), new StopCMD(console));

                    System.out.println(NAME + " started in " + ((double) (System.currentTimeMillis() - Lava.START_TIME) / 1000) + " seconds!");
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
