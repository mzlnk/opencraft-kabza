package pl.opencraft.kabza.nbtserializer.dto;

import lombok.Data;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
public class ItemStackDto {

    private String version;
    private NbtTagDto rootTag;

    public ItemStackDto() {
    }

    public ItemStackDto(String version, NbtTagDto rootTag) {
        this.version = version;
        this.rootTag = rootTag;
    }

    public NbtTagDto getRoot() {
        return (rootTag == null) ? new NbtTagDto() : rootTag;
    }

}
