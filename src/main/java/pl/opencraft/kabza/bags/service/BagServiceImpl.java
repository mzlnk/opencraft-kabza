package pl.opencraft.kabza.bags.service;

import pl.opencraft.kabza.bags.repository.BagRepository;
import pl.opencraft.kabza.bags.repository.BagRepositoryImpl;
import pl.opencraft.kabza.bags.repository.dto.Bag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagServiceImpl implements BagService{

    private BagRepository bagRepository = new BagRepositoryImpl();

    @Override
    public Optional<Bag> findBag(UUID uuid) {
        return bagRepository.findBag(uuid);
    }

    @Override
    public List<Bag> findAll() {
        return bagRepository.findAll();
    }

    @Override
    public void createOrUpdateBag(Bag bag) {
        bagRepository.createOrUpdateBag(bag);
    }

}
