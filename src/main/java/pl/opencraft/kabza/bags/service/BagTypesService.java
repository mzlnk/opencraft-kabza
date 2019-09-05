package pl.opencraft.kabza.bags.service;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.BagType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface BagTypesService {

    Optional<BagType> findBagType(String id);
    Optional<BagType> fromRecipe(ItemStack[] matrix);
    void createOrUpdateBagType(BagType bagType);
    List<BagType> findAll();
    void reload();

}
