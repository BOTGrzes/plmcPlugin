package pl.polskaliga.plmcplugin.utils;

import java.util.List;
import org.bukkit.ChatColor;

public class ColorFixer {
    public static String addColors(String input) {
        if (input != null && !input.isEmpty()) {
            input = ChatColor.translateAlternateColorCodes('&', input);
            return input;
        } else {
            return input;
        }
    }

    public static List<String> addColors(List<String> input) {
        if (input != null && !input.isEmpty()) {
            input.replaceAll(s -> addColors((String) s));

            return input;
        } else {
            return input;
        }
    }
}
