package module;

import module.yamlfile.ConfigManager;
import org.bukkit.ChatColor;

public class ModuleMain extends ConfigManager {

    public static String color (String msg) {
        if (msg.contains("&"))
            return msg.replace('&', ChatColor.COLOR_CHAR);
        return msg;
    }
}