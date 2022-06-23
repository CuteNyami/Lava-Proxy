package me.cutenyami.lava.console;

import me.cutenyami.lava.Lava;
import me.cutenyami.lava.console.color.ConsoleColors;
import me.cutenyami.lava.console.listener.IConsoleListener;
import me.cutenyami.lava.console.sender.IConsoleSender;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Console implements Runnable, Closeable, IConsoleSender {

    private final Thread thread = new Thread(this);

    private boolean running;

    private final List<IConsoleListener> listeners = new ArrayList<>();

    private final Terminal terminal;

    private final LineReader reader;

    private String line;

    public Console() throws IOException {
        this.terminal = TerminalBuilder.builder().system(true).color(true).jansi(true).build();
        this.reader = LineReaderBuilder.builder().terminal(this.terminal).build();
    }

    public void start() {
        if (!this.running) {
            this.thread.start();
        }
    }

    public void run() {
        this.running = true;
        this.listeners.forEach(listener -> listener.handleInit(this));
        while (this.running && (this.line = this.reader.readLine(ConsoleColors.translateColorCodes('§', Lava.version + " §r=> "))) != null) {
            Lava.getInstance().getCommandManager().dispatchCommand(this, this);
            this.listeners.forEach(listener -> listener.handleInput(this, this.line));
        }
        this.listeners.forEach(listener -> listener.handleClose(this));
    }

    public void close() {
        this.running = false;
    }

    public void sendMessage(Object... messages) {
        for (Object message : messages) {
            System.out.println(ConsoleColors.translateColorCodes('§', (String) message));
            //ServerLogger.getLogger().log(LogLevel.INFO, message);
        }
    }

    public Console addListener(IConsoleListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public List<IConsoleListener> getListeners() {
        return this.listeners;
    }

    public Terminal getTerminal() {
        return this.terminal;
    }

    public LineReader getReader() {
        return this.reader;
    }

    public String getLine() {
        return this.line;
    }
}
