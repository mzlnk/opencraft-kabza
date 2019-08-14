package pl.opencraft.kabza.nbtserializer;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public interface NbtSerializer {

    Optional<ItemStackDto> serialize(ItemStack itemStack);
    Optional<ItemStack> deserialize(ItemStackDto itemStackDto);

    ItemStack createOrUpdateNbtTags(ItemStack itemStack, String id, Map<String, NbtTagDto> tags);
    Map<String, NbtTagDto> readNbtTags(ItemStack itemStack, String id);
    ItemStack removeNbtTags(ItemStack itemStack, String id, String... tags);

}
