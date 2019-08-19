package pl.opencraft.kabza.bags.repository;

import pl.opencraft.kabza.bags.repository.dto.Bag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface BagsRepository {

    Optional<Bag> findBag(UUID uuid);
    List<Bag> findAll();
    void createOrUpdateBag(Bag bag);
    void removeBag(UUID uuid);
    void reload();

}
