package pl.opencraft;

import org.bukkit.plugin.java.JavaPlugin;
import pl.opencraft.kabza.bags.service.BagService;
import pl.opencraft.kabza.bags.service.BagServiceImpl;
import pl.opencraft.kabza.commands.executor.KabzaCommandExecutor;

import static org.bukkit.ChatColor.*;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaPlugin extends JavaPlugin {

    public static final String PREFIX = BLUE + "[Kabza]" + RESET;

    public static KabzaPlugin plugin;

    public BagService bagService;

    public KabzaPlugin() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        bagService = new BagServiceImpl();

        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("kabza").setExecutor(new KabzaCommandExecutor());
    }

}
