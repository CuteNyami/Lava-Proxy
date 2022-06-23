package me.cutenyami.lava;

import me.cutenyami.lava.command.CommandManager;
import me.cutenyami.lava.console.color.ConsoleColors;
import me.cutenyami.lava.dependency.DependencyLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProxyServer {

    public static String version = "Lava_0.0.1_SNAPSHOT";

    private static ProxyServer instance;

    private final DependencyLoader loader = new DependencyLoader();

    private final CommandManager commandManager = new CommandManager();

    public ProxyServer() {
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

    private void loadProperties() {
        System.out.println("Loading properties...");

        File file = new File("proxy.properties");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<Object, Object> defaultProperties = new HashMap<Object, Object>();
            defaultProperties.put("proxy-name", this.getName());
            defaultProperties.put("proxy-ip", "0.0.0.0");
            defaultProperties.put("proxy-port", 19132);
            defaultProperties.put("xbox-auth", true);

            /*
            Config config = new Config(file, Config.PROPERTIES);
            config.setAll(defaultProperties);
            config.save();
             */
        }
    }

    public static void init() {
        instance = new ProxyServer();
    }

    public static void println(String message) {
        System.out.println(ConsoleColors.translateColorCodes('§', message));
    }

    public static ProxyServer getInstance() {
        return instance;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public String getName() {
        return Lava.NAME;
    }
}
