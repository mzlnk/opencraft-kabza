package pl.opencraft.kabza.nbtserializer;

import lombok.NoArgsConstructor;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@NoArgsConstructor
public class NbtSerializationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Unable to serialize itemStack object";
    }

}
