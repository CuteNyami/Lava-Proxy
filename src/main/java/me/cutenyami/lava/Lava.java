package me.cutenyami.lava;

import me.cutenyami.lava.command.CommandManager;
import me.cutenyami.lava.console.color.ConsoleColors;
import me.cutenyami.lava.dependency.DependencyLoader;

import java.io.File;

public class Lava {

    public static String version = "Lava_0.0.1_SNAPSHOT";

    private static Lava instance;

    private final DependencyLoader loader = new DependencyLoader();

    private final CommandManager commandManager = new CommandManager();

    public Lava() {
        File folder = new File("dependencies");

        if (!folder.exists()) {
            folder.mkdir();
        }

        System.out.println("Loading dependencies, please wait...");

        this.loader.getDependencies().forEach(dependency -> {
            this.loader.download(dependency);
            this.loader.init(dependency);
        });
    }

    public static void init() {
        instance = new Lava();
    }

    public static void println(String message) {
        System.out.println(ConsoleColors.translateColorCodes('§', message));
    }

    public static Lava getInstance() {
        return instance;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }
}
