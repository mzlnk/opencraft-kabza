package pl.opencraft.kabza.bags.service;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.bags.repository.dto.BagType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface BagsService {

    Optional<Bag> findBag(UUID uuid);
    List<Bag> findAll();
    void updateBag(Bag bag);
    Bag createNewBag(String bagTypeId);
    void removeBag(UUID uuid);

    boolean isBag(ItemStack itemStack);
    Optional<Bag> fromItemStack(ItemStack itemStack);

    void reload();

}
