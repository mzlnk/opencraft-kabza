package pl.opencraft.kabza.bags.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
@AllArgsConstructor
public class Bag {

    private UUID uuid;
    private boolean opened;
    private List<Material> allowedItems;
    private List<BagItem> content;

}
