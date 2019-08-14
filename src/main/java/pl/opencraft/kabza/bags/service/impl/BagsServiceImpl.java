package pl.opencraft.kabza.bags.service.impl;

import pl.opencraft.kabza.bags.repository.BagsRepository;
import pl.opencraft.kabza.bags.repository.impl.BagsRepositoryImpl;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.bags.service.BagsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class BagsServiceImpl implements BagsService {

    private BagsRepository bagsRepository = new BagsRepositoryImpl();

    @Override
    public Optional<Bag> findBag(UUID uuid) {
        return bagsRepository.findBag(uuid);
    }

    @Override
    public List<Bag> findAll() {
        return bagsRepository.findAll();
    }

    @Override
    public void createOrUpdateBag(Bag bag) {
        bagsRepository.createOrUpdateBag(bag);
    }

}
