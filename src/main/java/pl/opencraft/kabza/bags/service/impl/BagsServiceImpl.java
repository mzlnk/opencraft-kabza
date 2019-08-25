package pl.opencraft.kabza.bags.service.impl;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.BagsRepository;
import pl.opencraft.kabza.bags.repository.impl.BagsRepositoryImpl;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.bags.service.BagsService;
import pl.opencraft.kabza.nbtserializer.NbtSerializationException;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static pl.opencraft.KabzaPlugin.*;

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
    public void updateBag(Bag bag) {
        bagsRepository.createOrUpdateBag(bag);
    }

    @Override
    public Bag createNewBag(String bagTypeId) {
        Bag bag = Bag.newInstance(bagTypeId);
        bagsRepository.createOrUpdateBag(bag);

        return bag;
    }

    @Override
    public void removeBag(UUID uuid) {
        bagsRepository.removeBag(uuid);
    }

    @Override
    public boolean isBag(ItemStack itemStack) {
        try {
            Map<String, NbtTagDto> tags = plugin.nbtSerializer.readNbtTags(itemStack, PLUGIN_NBT_KEY_ID);
            NbtTagDto identityTag = tags.get("Indentity");

            if(identityTag == null || identityTag.getTagString().equals(BAG_NBT_IDENTITY)) {
                return false;
            }
        } catch (NbtSerializationException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Bag> fromItemStack(ItemStack itemStack) {
        if(!this.isBag(itemStack)) {
            return Optional.empty();
        }

        Map<String, NbtTagDto> tags = plugin.nbtSerializer.readNbtTags(itemStack, PLUGIN_NBT_KEY_ID);
        return bagsRepository.findBag(UUID.fromString(tags.get("Uuid").getTagString()));
    }

    @Override
    public void reload() {
        bagsRepository.reload();
    }

}
