package pl.opencraft.kabza.nbtserializer.converters;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.Map;
import java.util.Optional;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class ItemStackFromItemStackDtoConverter {

    private static final String ERR_INVALID_VERSION =
            "Could not deserialize ItemStack cause invalid version (expected {version}) - dto: {dto}";

    private static final String VERSION = "v1_14_R1";

    public static Optional<ItemStack> convert(ItemStackDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
        String version = dto.getVersion();
        if (!VERSION.equals(version)) {
            plugin.getLogger().warning(ERR_INVALID_VERSION.replace("{version}", VERSION).replace("{dto}", dto.toString()));
            return Optional.empty();
        }
        NBTBase rootTag = convertNbtTag(dto.getRootTag());
        if (rootTag != null && rootTag instanceof NBTTagCompound) {
            NBTTagCompound tagCompound = (NBTTagCompound) rootTag;
            net.minecraft.server.v1_14_R1.ItemStack nmsItem = net.minecraft.server.v1_14_R1.ItemStack.a(tagCompound);
            return Optional.of(CraftItemStack.asBukkitCopy(nmsItem));
        }
        return Optional.empty();
    }

    private static NBTBase convertNbtTag(NbtTagDto dto) {
        NbtTagType type = dto.getType();
        switch (type) {
            case BYTE:
                return new NBTTagByte(dto.getTagByte());
            case SHORT:
                return new NBTTagShort(dto.getTagShort());
            case INT:
                return new NBTTagInt(dto.getTagInt());
            case LONG:
                return new NBTTagLong(dto.getTagLong());
            case FLOAT:
                return new NBTTagFloat(dto.getTagFloat());
            case DOUBLE:
                return new NBTTagDouble(dto.getTagDouble());
            case BYTE_ARRAY:
                return new NBTTagByteArray(dto.getTagByteArray());
            case STRING:
                return new NBTTagString(dto.getTagString());
            case LIST:
                return convertNbtTagList(dto);
            case COMPOUND:
                return convertNbtTagCompound(dto);
            case INT_ARRAY:
                return new NBTTagIntArray(dto.getTagIntArray());
            default:
                return null;
        }
    }

    private static NBTBase convertNbtTagList(NbtTagDto dto) {
        NBTTagList tagList = new NBTTagList();
        for (NbtTagDto tagDto : dto.getTagList()) {
            tagList.add(convertNbtTag(tagDto));
        }
        return tagList;
    }

    private static NBTBase convertNbtTagCompound(NbtTagDto dto) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        Map<String, NbtTagDto> tagDto = dto.getTagCompound();
        for (String key : tagDto.keySet()) {
            NbtTagDto value = tagDto.get(key);
            tagCompound.set(key, convertNbtTag(value));
        }
        return tagCompound;
    }

}
