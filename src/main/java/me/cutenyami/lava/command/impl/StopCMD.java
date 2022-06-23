package me.cutenyami.lava.command.impl;

import me.cutenyami.lava.command.Command;
import me.cutenyami.lava.console.Console;
import me.cutenyami.lava.console.sender.IConsoleSender;

public class StopCMD extends Command {

    private final Console console;

    public StopCMD(Console console) {
        super(new String[] { "stop", "end" }, "Stop the proxy");
        this.console = console;
    }

    public void onCommand(IConsoleSender sender, String[] args) {
        this.console.close();
    }
}
