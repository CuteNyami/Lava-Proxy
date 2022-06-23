package me.cutenyami.lava.command;

import me.cutenyami.lava.console.sender.IConsoleSender;

public abstract class Command {
    private final String[] aliases;

    private final String name;

    private final String description;

    public Command(String name, String description) {
        this.aliases = null;
        this.name = name;
        this.description = description;
    }

    public Command(String[] name, String description) {
        this.aliases = name;
        this.name = aliases[0];
        this.description = description;
    }

    public Command(String name) {
        this.aliases = null;
        this.name = name;
        this.description = null;
    }

    public Command(String[] aliases) {
        this.aliases = aliases;
        this.description = null;
        this.name = aliases[0];
    }

    public abstract void onCommand(IConsoleSender sender, String[] args);

    public String[] getAliases() {
        return this.aliases;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        if (this.description != null) {
            return this.description;
        }
        return "This is a default description!";
    }
}

