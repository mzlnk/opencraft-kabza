package pl.opencraft.kabza.bags.repository;

import pl.opencraft.kabza.bags.repository.dto.Bag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagRepositoryImpl implements BagRepository {

    @Override
    public Optional<Bag> findBag(UUID uuid) {
        return null; // todo: code here
    }

    @Override
    public List<Bag> findAll() {
        return null; // todo: code here
    }

    @Override
    public void createOrUpdateBag(Bag bag) {

    }

}
