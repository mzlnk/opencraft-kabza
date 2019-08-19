package pl.opencraft;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.opencraft.kabza.bags.service.BagTypesService;
import pl.opencraft.kabza.bags.service.BagsService;
import pl.opencraft.kabza.bags.service.impl.BagTypesServiceImpl;
import pl.opencraft.kabza.bags.service.impl.BagsServiceImpl;
import pl.opencraft.kabza.commands.executor.KabzaCommandExecutor;
import pl.opencraft.kabza.listeners.CraftBagListener;
import pl.opencraft.kabza.nbtserializer.NbtSerializer;
import pl.opencraft.kabza.nbtserializer.NbtSerializerImpl;
import pl.opencraft.kabza.utils.FileUtil;

import static org.bukkit.ChatColor.*;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaPlugin extends JavaPlugin {

    public static final String BAG_NBT_ID = "KabzaBag";
    public static final String PREFIX = BLUE + "[Kabza]" + RESET;

    public static KabzaPlugin plugin;

    public FileUtil fileUtil;
    public NbtSerializer nbtSerializer;

    public BagsService bagsService;
    public BagTypesService bagTypesService;

    public KabzaPlugin() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        nbtSerializer =new NbtSerializerImpl();

        bagsService = new BagsServiceImpl();
        bagTypesService = new BagTypesServiceImpl();

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("kabza").setExecutor(new KabzaCommandExecutor());
    }

    private void registerListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new CraftBagListener(), this);
    }

}
