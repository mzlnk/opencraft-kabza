package pl.opencraft.kabza.bags.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.opencraft.kabza.bags.repository.BagsRepository;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.utils.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagsRepositoryImpl implements BagsRepository {

    private Map<UUID, Bag> bags = new HashMap<>();

    public BagsRepositoryImpl() {
        loadBags();
    }

    @Override
    public Optional<Bag> findBag(UUID uuid) {
        return Optional.ofNullable(bags.get(uuid));
    }

    @Override
    public List<Bag> findAll() {
        return new ArrayList<>(bags.values());
    }

    @Override
    public void createOrUpdateBag(Bag bag) {
        bags.put(bag.getUuid(), bag);
        this.saveBag(bag);
    }

    private void loadBags() {
        plugin.fileUtil.listFiles(FileUtil.Directory.BAGS).stream().forEach(file -> {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                Bag bag = new Gson().fromJson(br, Bag.class);
                bags.put(bag.getUuid(), bag);
            } catch (Exception e) {
                plugin.getLogger().warning("Could not read file: " + file.getName());
            }
        });
        plugin.getLogger().info("Loaded " + bags.size() + " bags");
    }

    private void saveBag(Bag bag) {
        String filename = bag.getUuid().toString() + ".json";
        String content = new GsonBuilder().setPrettyPrinting().create().toJson(bag);
        plugin.fileUtil.createOrUpdateFile(FileUtil.Directory.BAGS, filename, content);
    }

}
