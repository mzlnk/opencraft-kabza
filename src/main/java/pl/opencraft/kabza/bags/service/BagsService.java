package pl.opencraft.kabza.bags.service;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.Bag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface BagsService {

    Optional<Bag> findBag(UUID uuid);
    List<Bag> findAll();
    void createOrUpdateBag(Bag bag);

    boolean isBag(ItemStack itemStack);
    Optional<Bag> fromItemStack(ItemStack itemStack);

}
