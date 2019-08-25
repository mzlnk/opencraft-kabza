package pl.opencraft.kabza.bags.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.opencraft.kabza.bags.repository.BagTypesRepository;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.utils.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

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
    public void createOrUpdateBagType(BagType bagType) {
        bagTypes.put(bagType.getId(), bagType);
        this.saveBagType(bagType);
    }

    @Override
    public void removeBagType(String id) {
        bagTypes.remove(id);
        plugin.fileUtil.removeFile(FileUtil.Directory.BAG_TYPES, id + ".json");
    }

    @Override
    public void reload() {
        bagTypes.clear();
        this.loadBagTypes();
    }

    private void loadBagTypes() {
        plugin.fileUtil.listFiles(FileUtil.Directory.BAG_TYPES).stream().forEach(file -> {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                BagType bagType = new Gson().fromJson(br, BagType.class);
                bagTypes.put(bagType.getId(), bagType);
            } catch (Exception e) {
                plugin.getLogger().warning("Could not read file: " + file.getName());
            }
        });

        plugin.getLogger().info("Loaded: " + bagTypes.size() + " bagTypes");
    }

    private void saveBagType(BagType bagType) {
        String filename = bagType.getId() + ".json";
        String content = new GsonBuilder().setPrettyPrinting().create().toJson(bagType);
        plugin.fileUtil.createOrUpdateFile(FileUtil.Directory.BAG_TYPES, filename, content);
    }

}
