package main;

import listener.PlayerEnteredRegion;
import module.yamlfile.ConfigManager;
import module.yamlfile.FileLoaderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static String prefix = ChatColor.GOLD + "[WorldGuard Region] ";
    private static FileLoaderAPI config = null;

    @Override
    public void onEnable () {
        System.out.println(getPrefix() + ChatColor.DARK_GREEN + "Loading..");
        plugin = this;
        onReload();

        Bukkit.getPluginManager().registerEvents(new PlayerEnteredRegion(), plugin);

        System.out.println(getPrefix() + ChatColor.DARK_GREEN + "Loaded!");
    }

    @Override
    public void onDisable () {
        System.out.println(getPrefix() + ChatColor.DARK_RED + "Unloaded!");
    }

    public void onReload() {
        this.regConfigYaml();
    }
    private void regConfigYaml() {
        Main.config = ConfigManager.getFileLoaderAPI(this, "\\", "config.yml", false);
    }

    public static Main getPlugin() {return plugin;}
    public static String getPrefix() {return prefix;}
    public static FileLoaderAPI getConfigFile() {return config;}

}
