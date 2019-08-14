package pl.opencraft.kabza.nbtserializer;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.nbtserializer.converters.ItemStackDtoFromItemStackConverter;
import pl.opencraft.kabza.nbtserializer.converters.ItemStackFromItemStackDtoConverter;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class NbtSerializer implements NbtSerializerFacade {

    @Override
    public Optional<ItemStackDto> serialize(ItemStack itemStack) {
        return ItemStackDtoFromItemStackConverter.convert(itemStack);
    }

    @Override
    public Optional<ItemStack> deserialize(ItemStackDto itemStackDto) {
        return ItemStackFromItemStackDtoConverter.convert(itemStackDto);
    }

    @Override
    public ItemStack createOrUpdateNbtTags(ItemStack itemStack, String id, Map<String, NbtTagDto> tags) {
        return this.serialize(itemStack)
                .map(dto -> {
                    NbtTagDto tagTagDto = dto.getRootTag().getFromCompound("tag");
                    if (tagTagDto == null) {
                        tagTagDto = new NbtTagDto(NbtTagType.COMPOUND);
                    }

                    Map<String, NbtTagDto> tagTagCompound = tagTagDto.getTagCompound();
                    tagTagCompound.put(id, new NbtTagDto(NbtTagType.COMPOUND).withTagCompoundValue(tags));

                    Map<String, NbtTagDto> rootTagCompound = dto.getRootTag().getTagCompound();
                    rootTagCompound.put("tag", new NbtTagDto(NbtTagType.COMPOUND).withTagCompoundValue(tagTagCompound));

                    dto.getRootTag().withTagCompoundValue(rootTagCompound);

                    return this.deserialize(dto);
                })
                .map(Optional::get)
                .orElseThrow(() -> new NbtSerializationException("Unable to create/update NBT tags with given ItemStack and/or id"));
    }

    @Override
    public Map<String, NbtTagDto> readNbtTags(ItemStack itemStack, String id) {
        return this.serialize(itemStack)
                .map(ItemStackDto::getRootTag)
                .map(rootTag -> rootTag.getFromCompound("tag"))
                .map(tagTag -> tagTag.getFromCompound(id))
                .map(NbtTagDto::getTagCompound)
                .orElseThrow(() -> new NbtSerializationException("Unable to read NBT tags with given ItemStack and/or id"));
    }

    @Override
    public ItemStack removeNbtTags(ItemStack itemStack, String id, String... tags) {
        Map<String, NbtTagDto> allTags = this.readNbtTags(itemStack, id);
        Arrays.stream(tags).forEach(allTags::remove);

        return this.createOrUpdateNbtTags(itemStack, id, allTags);
    }

}
