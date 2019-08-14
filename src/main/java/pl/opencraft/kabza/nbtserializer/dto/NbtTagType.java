package pl.opencraft.kabza.nbtserializer.dto;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public enum NbtTagType {

    END,
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BYTE_ARRAY,
    STRING,
    LIST,
    COMPOUND,
    INT_ARRAY;

    public static NbtTagType findById(int id) {
        if (id < 0 || id >= values().length) {
            return null;
        }
        return values()[id];
    }

}
