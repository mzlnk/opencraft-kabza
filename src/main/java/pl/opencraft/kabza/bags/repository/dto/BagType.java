package pl.opencraft.kabza.bags.repository.dto;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Getter
@Builder
public class BagType implements Identifiable {

    private String id;
    private String bagName;
    private List<String> bagDescription;
    private Material bagItemType;
    private boolean craftingEnabled;
    private boolean shapelessCrafting;
    private Material[] craftingRecipe;

    private boolean allItemsAllowed;

    private List<BagTypeItem> allowedItems;


}
