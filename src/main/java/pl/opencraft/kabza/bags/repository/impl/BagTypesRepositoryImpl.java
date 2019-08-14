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

            BagType bagType = BagType.builder()
                    .bagId(config.getString("bag_id", file.getName().substring(0, file.getName().length() - 5)))
                    .bagName(config.getString("bag_name", "Kabza"))
                    .bagDescription(config.getStringList("bag_description"))
                    .bagItemType(Material.getMaterial(config.getString("bag_item_type", "CHEST")))
                    .craftingEnabled(config.getBoolean("crafting_enabled", false))
                    .craftingRecipe((Material[]) config.getStringList("crafting_recipe").toArray())
                    .allowedItems(config.getStringList("allowed_items").stream().map(Material::getMaterial).collect(Collectors.toList()))
                    .build();

            bagTypes.put(bagType.getBagId(), bagType);
        });
    }

}
