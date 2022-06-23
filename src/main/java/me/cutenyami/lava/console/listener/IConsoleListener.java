package me.cutenyami.lava.console.listener;

import me.cutenyami.lava.console.Console;

public interface IConsoleListener {

    void handleInit(Console console);

    void handleInput(Console console, String line);

    void handleClose(Console console);

}
