package pl.opencraft.kabza.bags.service.impl;

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
    public List<BagType> findAll() {
        return bagTypesRepository.findAll();
    }

    @Override
    public void reload() {
        bagTypesRepository.reload();
    }

}
