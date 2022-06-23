package me.cutenyami.lava.command.impl;

import me.cutenyami.lava.ProxyServer;
import me.cutenyami.lava.command.Command;
import me.cutenyami.lava.console.sender.IConsoleSender;

import java.util.HashMap;
import java.util.Map;

public class HelpCMD extends Command {
    public HelpCMD() {
        super(new String[] { "help", "?" }, "Display all commands");
    }

    public void onCommand(IConsoleSender sender, String[] args) {
        /*
        Lava.getInstance().getCommandManager().getCommandList().forEach(command -> {
            if (command.getAliases() != null) {
                sender.sendMessage(Arrays.toString(command.getAliases()) + " | " + command.getDescription());
            } else if (command.getName() != null) {
                sender.sendMessage("[" + command.getName() + "] | " + command.getDescription());
            }
        });

         */
        int pageNumber = 1;
        int pageHeight = 8;
        /*
        if (sender.isPlayer()) {
            if (args.length >= 1) {
                try {
                    pageNumber = Integer.parseInt(args[0]);
                    if (pageNumber < 1) pageNumber = 1;
                } catch (NumberFormatException ignored) {
                }
            }
        } else {
            pageHeight = Integer.MAX_VALUE;
        }
         */

        pageHeight = Integer.MAX_VALUE;

        Map<String, Command> commands = new HashMap<>();
        for (Command command : ProxyServer.getInstance().getCommandManager().getCommandList()) {
            /*
            if (sender.hasPermission(command.getPermission())) {
                commands.put(command.getName(), command);
            }
             */
            commands.put(command.getName(), command);
        }

        int pages = commands.size() % pageHeight == 0 ? commands.size() / pageHeight : commands.size() / pageHeight + 1;
        pageNumber = Math.min(pageNumber, pages);
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        sender.sendMessage("Help page " + pageNumber + "/" + pages);

        int i = 1;
        for (Command command : commands.values()) {
            if (i >= (pageNumber - 1) * pageHeight + 1 && i <= Math.min(commands.size(), pageNumber * pageHeight)) {
                sender.sendMessage("§6/" + command.getName() + ": §r" + command.getDescription());
            }
            i++;
        }
    }
}
