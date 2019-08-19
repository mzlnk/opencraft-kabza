package pl.opencraft.kabza.bags.repository.impl;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.opencraft.kabza.bags.repository.BagTypesRepository;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.utils.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagTypesRepositoryImpl implements BagTypesRepository {

    private Map<String, BagType> bagTypes = new HashMap<>();

    public BagTypesRepositoryImpl() {
        loadBagTypes();
    }

    @Override
    public Optional<BagType> findBagType(String id) {
        return Optional.ofNullable(bagTypes.get(id));
    }

    @Override
    public List<BagType> findAll() {
        return new ArrayList<>(bagTypes.values());
    }

    @Override
    public void reload() {
        bagTypes.clear();
        this.loadBagTypes();
    }

    private void loadBagTypes() {
        plugin.fileUtil.listFiles(FileUtil.Directory.BAG_TYPES).stream().forEach(file -> {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            String bagTypeId = config.getString("bag_type_id", file.getName().substring(0, file.getName().length() - 5));
            String bagName = config.getString("bag_name", "Kabza");
            List<String> bagDescription = config.getStringList("bag_description");
            Material bagItemType = Material.getMaterial(config.getString("bag_item_type", "CHEST"));
            boolean craftingEnabled = config.getBoolean("crafting_enabled", false);
            List<Material> allowedItems = config.getStringList("allowed_items").stream()
                    .map(Material::getMaterial).collect(Collectors.toList());

            Material[] craftingRecipe = new Material[9];
            for(int i = 0; i < 9; i++) {
                craftingRecipe[i] = null;
            }
            int i = 0;
            for(String s : config.getStringList("crafting_recipe")) {
                plugin.getLogger().info("[R] [" + i + "] = " + s);
                craftingRecipe[i++] = Material.getMaterial(s);
            }

            BagType bagType = BagType.builder()
                    .bagTypeId(bagTypeId)
                    .bagName(bagName)
                    .bagDescription(bagDescription)
                    .bagItemType(bagItemType)
                    .craftingEnabled(craftingEnabled)
                    .craftingRecipe(craftingRecipe)
                    .allowedItems(allowedItems)
                    .build();

            bagTypes.put(bagType.getBagTypeId(), bagType);
        });

        plugin.getLogger().info("Loaded: " + bagTypes.size() + " bagTypes");
    }

}
