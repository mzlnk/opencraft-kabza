package pl.opencraft.kabza.bags.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
@AllArgsConstructor
public class BagItem {

    private Material type;
    private int amount;

}
