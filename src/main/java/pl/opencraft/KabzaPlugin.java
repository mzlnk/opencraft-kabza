package pl.opencraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.opencraft.kabza.KabzaApi;
import pl.opencraft.kabza.bags.service.BagTypesService;
import pl.opencraft.kabza.bags.service.BagsService;
import pl.opencraft.kabza.bags.service.impl.BagTypesServiceImpl;
import pl.opencraft.kabza.bags.service.impl.BagsServiceImpl;
import pl.opencraft.kabza.commands.executor.KabzaCommandExecutor;
import pl.opencraft.kabza.listeners.CollectItemListener;
import pl.opencraft.kabza.listeners.CraftBagListener;
import pl.opencraft.kabza.listeners.OpenBagListener;
import pl.opencraft.kabza.messages.MessageBundle;
import pl.opencraft.kabza.nbtserializer.NbtSerializer;
import pl.opencraft.kabza.nbtserializer.NbtSerializerImpl;
import pl.opencraft.kabza.utils.FileUtil;

import static org.bukkit.ChatColor.BLUE;
import static org.bukkit.ChatColor.RESET;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaPlugin extends JavaPlugin implements KabzaApi {

    public static final String PLUGIN_NBT_KEY_ID = "KabzaPlugin";
    public static final String BAG_NBT_IDENTITY = "KabzaBag";

    public static final String PREFIX = BLUE + "[Kabza] " + RESET;

    public static KabzaPlugin plugin;

    public FileUtil fileUtil;
    public NbtSerializer nbtSerializer;

    public MessageBundle messages;

    public BagsService bagsService;
    public BagTypesService bagTypesService;

    public KabzaPlugin() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        createContext();
        createMessageBundle();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public BagsService getBagsService() {
        return bagsService;
    }

    @Override
    public BagTypesService getBagTypesService() {
        return bagTypesService;
    }

    private void createMessageBundle() {
        FileConfiguration config = getConfig();
        String locale = config.getString("locale", "pl");
        messages = new MessageBundle(locale);
    }

    private void createContext() {
        fileUtil = new FileUtil(this);
        nbtSerializer = new NbtSerializerImpl();

        bagsService = new BagsServiceImpl();
        bagTypesService = new BagTypesServiceImpl();
    }

    private void registerCommands() {
        this.getCommand("kabza").setExecutor(new KabzaCommandExecutor());
    }

    private void registerListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new CraftBagListener(), this);
        pluginManager.registerEvents(new CollectItemListener(), this);
        pluginManager.registerEvents(new OpenBagListener(), this);
    }

}
