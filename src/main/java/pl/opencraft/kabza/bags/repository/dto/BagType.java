package pl.opencraft.kabza.bags.repository.dto;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Getter
@Builder
public class BagType implements Identifiable {

    private String id;
    private String bagName;

    @Builder.Default
    private List<String> bagDescription = new ArrayList<>();

    private Material bagItemType;
    private boolean craftingEnabled;
    private boolean shapelessCrafting;

    @Builder.Default
    private Material[] craftingRecipe = new Material[9];

    private boolean allItemsAllowed;

    @Builder.Default
    private List<BagTypeItem> allowedItems = new ArrayList<>();


}
