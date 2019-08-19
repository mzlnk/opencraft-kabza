package pl.opencraft.kabza.bags.service.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.BagTypesRepository;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.bags.repository.impl.BagTypesRepositoryImpl;
import pl.opencraft.kabza.bags.service.BagTypesService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagTypesServiceImpl implements BagTypesService {

    private BagTypesRepository bagTypesRepository = new BagTypesRepositoryImpl();

    @Override
    public Optional<BagType> findBagType(String id) {
        return bagTypesRepository.findBagType(id);
    }

    @Override
    public Optional<BagType> fromRecipe(ItemStack[] matrix) {
        return this.findAll().stream()
                .filter(BagType::isCraftingEnabled)
                .filter(bagType -> {
                    Material[] recipe = bagType.getCraftingRecipe();
                    for (int i = 0; i < 9; i++) {
                        if (matrix[i] == null && recipe[i] == null) continue;
                        if (matrix[i] == null && recipe[i].equals(Material.AIR)) continue;
                        if (matrix[i] == null && recipe[i] != null) return false;
                        if (!matrix[i].getType().equals(recipe[i])) return false;
                        if (matrix[i].getAmount() != 1) return false;
                    }
                    return true;
                })
                .findFirst();
    }

    @Override
    public List<BagType> findAll() {
        return bagTypesRepository.findAll();
    }

    @Override
    public void reload() {
        bagTypesRepository.reload();
    }

}
