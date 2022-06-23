package me.cutenyami.lava.command;

import me.cutenyami.lava.console.Console;
import me.cutenyami.lava.console.color.ConsoleColors;
import me.cutenyami.lava.console.sender.IConsoleSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandManager {
    public HashMap<String, Command> commands = new HashMap<>();

    private final List<Command> commandList = new ArrayList<>();

    public void register(Command... command) {
        for (Command command1 : command) {
            if (command1.getAliases() != null) {
                for (String aliases : command1.getAliases()) {
                    this.commands.put(aliases, command1);
                }
            }

            if (command1.getName() != null) {
                this.commands.put(command1.getName(), command1);
            }

            this.commandList.add(command1);
        }
    }

    public void dispatchCommand(IConsoleSender sender, Console console) {
        String[] args = console.getLine().split(" ");
        String label = args[0].toLowerCase();
        Command command = this.commands.get(label);
        if (command == null) {
            String text = ConsoleColors.translateColorCodes('§', "§4Unknown command. Type ? or help for help.");
            sender.sendMessage(text);
        } else {
            String[] rangedArgs = Arrays.copyOfRange(args, 1, args.length);
            command.onCommand(sender, rangedArgs);
        }
    }

    public Command getCommandList(Class<? extends Command> clazz) {
        return this.commandList.stream().filter(command -> command.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public List<Command> getCommandList() {
        return this.commandList;
    }
}

