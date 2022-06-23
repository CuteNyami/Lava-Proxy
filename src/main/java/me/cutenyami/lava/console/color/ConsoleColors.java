package me.cutenyami.lava.console.color;

import org.fusesource.jansi.Ansi;

public enum ConsoleColors {
    DEFAULT('r', Ansi.ansi().fg(Ansi.Color.DEFAULT).reset()),
    BLACK('0', Ansi.ansi().fg(Ansi.Color.BLACK)),
    WHITE('f', Ansi.ansi().fg(Ansi.Color.WHITE)),
    RED('c', Ansi.ansi().fg(Ansi.Color.RED)),
    DARK_RED('4', Ansi.ansi().fg(Ansi.Color.RED).bold()),
    GREEN('a', Ansi.ansi().fg(Ansi.Color.GREEN)),
    DARK_GREEN('2', Ansi.ansi().fg(Ansi.Color.GREEN).bold()),
    BLUE('9', Ansi.ansi().fg(Ansi.Color.BLUE)),
    DARK_BLUE('1', Ansi.ansi().fg(Ansi.Color.BLUE).bold()),
    CYAN('d', Ansi.ansi().fg(Ansi.Color.CYAN)),
    DARK_CYAN('3', Ansi.ansi().fg(Ansi.Color.CYAN).bold()),
    MAGENTA('d', Ansi.ansi().fg(Ansi.Color.MAGENTA)),
    DARK_MAGENTA('5', Ansi.ansi().fg(Ansi.Color.MAGENTA).bold()),
    YELLOW('e', Ansi.ansi().fg(Ansi.Color.YELLOW)),
    DARK_YELLOW('6', Ansi.ansi().fg(Ansi.Color.YELLOW).bold());

    final char index;

    final String data;

    ConsoleColors(char index, Ansi data) {
        this.index = index;
        this.data = data.toString();
    }

    public static String translateColorCodes(char replacement, String string) {
        for (ConsoleColors value : values()) {
            string = string.replace(String.valueOf(replacement) + value.index, value.toString());
        }
        return string + DEFAULT;
    }

    public String toString() {
        return this.data;
    }
}
