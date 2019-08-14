package pl.opencraft.kabza.nbtserializer.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
public class NbtTagDto {

    private NbtTagType type;
    private Byte tagByte;
    private Short tagShort;
    private Integer tagInt;
    private Long tagLong;
    private Float tagFloat;
    private Double tagDouble;
    private byte[] tagByteArray;
    private String tagString;
    private List<NbtTagDto> tagList;
    private Map<String, NbtTagDto> tagCompound;
    private int[] tagIntArray;

    public NbtTagDto() {
        this.type = NbtTagType.END;
    }

    public NbtTagDto(NbtTagType type) {
        this.type = type;
    }

    public NbtTagDto withTagByteValue(Byte value) {
        this.tagByte = value;
        return this;
    }

    public NbtTagDto withTagShortValue(Short value) {
        this.tagShort = value;
        return this;
    }

    public NbtTagDto withTagIntValue(Integer value) {
        this.tagInt = value;
        return this;
    }

    public NbtTagDto withTagLongValue(Long value) {
        this.tagLong = value;
        return this;
    }

    public NbtTagDto withTagFloatValue(Float value) {
        this.tagFloat = value;
        return this;
    }

    public NbtTagDto withTagDoubleValue(Double value) {
        this.tagDouble = value;
        return this;
    }

    public NbtTagDto withTagByteArrayValue(byte[] value) {
        this.tagByteArray = value;
        return this;
    }

    public NbtTagDto withTagStringValue(String value) {
        this.tagString = value;
        return this;
    }

    public NbtTagDto withTagListValue(List<NbtTagDto> value) {
        this.tagList = value;
        return this;
    }

    public NbtTagDto withTagCompoundValue(Map<String, NbtTagDto> value) {
        this.tagCompound = value;
        return this;
    }

    public NbtTagDto withTagIntArrayValue(int[] value) {
        this.tagIntArray = value;
        return this;
    }

    public NbtTagDto getFromCompound(String key) {
        Map<String, NbtTagDto> map = this.getTagCompound();
        if (map != null) {
            NbtTagDto value = map.get(key);
            if (value != null) {
                return value;
            }
        }
        return new NbtTagDto();
    }

    public int getListSize() {
        List<NbtTagDto> list = this.getTagList();
        return (list == null) ? 0 : list.size();
    }

    public NbtTagDto getFromList(int idx) {
        int size = this.getListSize();
        return (idx < 0 || idx >= size) ? new NbtTagDto() : this.getTagList().get(idx);
    }

}
