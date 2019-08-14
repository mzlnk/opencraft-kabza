package pl.opencraft.kabza.nbtserializer.converters;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.*;

import static pl.opencraft.kabza.nbtserializer.dto.NbtTagType.*;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class ItemStackDtoFromItemStackConverter {

    private static final String VERSION = "v1_14_R1";

    public static Optional<ItemStackDto> convert(ItemStack itemStack) {
        if (itemStack == null) {
            return Optional.empty();
        }
        NBTTagCompound tagCompound = new NBTTagCompound();
        net.minecraft.server.v1_14_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        nmsItemStack.save(tagCompound);
        NbtTagDto rootTag = convertNbtTag(tagCompound);
        return Optional.of(new ItemStackDto(VERSION, rootTag));
    }

    private static NbtTagDto convertNbtTag(NBTBase tag) {
        NbtTagType type = NbtTagType.findById(tag.getTypeId());
        switch (type) {
            case BYTE:
                return convertNbtTagByte(tag);
            case SHORT:
                return convertNbtTagShort(tag);
            case INT:
                return convertNbtTagInt(tag);
            case LONG:
                return convertNbTagLong(tag);
            case FLOAT:
                return convertNbtTagFloat(tag);
            case DOUBLE:
                return convertNbtTagDouble(tag);
            case BYTE_ARRAY:
                return convertNbtTagByteArray(tag);
            case STRING:
                return convertNbtTagString(tag);
            case LIST:
                return convertNbtTagList(tag);
            case COMPOUND:
                return convertNbtTagCompound(tag);
            case INT_ARRAY:
                return convertNbtTagIntArray(tag);
            default:
                return new NbtTagDto(END);
        }
    }

    private static NbtTagDto convertNbtTagByte(NBTBase tag) {
        NBTTagByte tagByte = (NBTTagByte) tag;
        return new NbtTagDto(BYTE).withTagByteValue(tagByte.asByte());
    }

    private static NbtTagDto convertNbtTagShort(NBTBase tag) {
        NBTTagShort tagShort = (NBTTagShort) tag;
        return new NbtTagDto(SHORT).withTagShortValue(tagShort.asShort());
    }

    private static NbtTagDto convertNbtTagInt(NBTBase tag) {
        NBTTagInt tagInt = (NBTTagInt) tag;
        return new NbtTagDto(INT).withTagIntValue(tagInt.asInt());
    }

    private static NbtTagDto convertNbTagLong(NBTBase tag) {
        NBTTagLong tagLong = (NBTTagLong) tag;
        return new NbtTagDto(LONG).withTagLongValue(tagLong.asLong());
    }

    private static NbtTagDto convertNbtTagFloat(NBTBase tag) {
        NBTTagFloat tagFloat = (NBTTagFloat) tag;
        return new NbtTagDto(FLOAT).withTagFloatValue(tagFloat.asFloat());
    }

    private static NbtTagDto convertNbtTagDouble(NBTBase tag) {
        NBTTagDouble tagDouble = (NBTTagDouble) tag;
        return new NbtTagDto(DOUBLE).withTagDoubleValue(tagDouble.asDouble());
    }

    private static NbtTagDto convertNbtTagByteArray(NBTBase tag) {
        NBTTagByteArray tagByteArray = (NBTTagByteArray) tag;
        return new NbtTagDto(BYTE_ARRAY).withTagByteArrayValue(tagByteArray.getBytes());
    }

    private static NbtTagDto convertNbtTagString(NBTBase tag) {
        NBTTagString tagString = (NBTTagString) tag;
        return new NbtTagDto(STRING).withTagStringValue(tagString.asString());
    }

    private static NbtTagDto convertNbtTagList(NBTBase tag) {
        NBTTagList tagList = (NBTTagList) tag;
        List<NbtTagDto> value = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            value.add(convertNbtTag(tagList.get(i)));
        }
        return new NbtTagDto(LIST).withTagListValue(value);
    }

    private static NbtTagDto convertNbtTagCompound(NBTBase tag) {
        NBTTagCompound tagCompound = (NBTTagCompound) tag;
        Map<String, NbtTagDto> value = new HashMap<>();
        for (String key : tagCompound.getKeys()) {
            value.put(key, convertNbtTag(tagCompound.get(key)));
        }
        return new NbtTagDto(COMPOUND).withTagCompoundValue(value);
    }

    private static NbtTagDto convertNbtTagIntArray(NBTBase tag) {
        NBTTagIntArray tagIntArray = (NBTTagIntArray) tag;
        return new NbtTagDto(INT_ARRAY).withTagIntArrayValue(tagIntArray.getInts());
    }

}
