package pl.opencraft.kabza.bags.repository;

import pl.opencraft.kabza.bags.repository.dto.BagType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface BagTypesRepository {

    Optional<BagType> findBagType(String id);
    List<BagType> findAll();
    void createOrUpdateBagType(BagType bagType);
    void removeBagType(String id);
    void reload();

}
