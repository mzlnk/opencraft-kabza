package pl.opencraft;

import org.bukkit.plugin.java.JavaPlugin;
import pl.opencraft.kabza.commands.executor.KabzaCommandExecutor;

import static org.bukkit.ChatColor.*;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaPlugin extends JavaPlugin {

    public static final String PREFIX = BLUE + "[Kabza]" + RESET;

    public static KabzaPlugin plugin;

    public KabzaPlugin() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("kabza").setExecutor(new KabzaCommandExecutor());
    }

}
