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
public class BagType {

    private String bagId;
    private String bagName;
    private List<String> bagDescription;
    private Material bagItemType;
    private boolean craftingEnabled;
    private Material[] craftingRecipe;

    @Builder.Default
    private List<Material> allowedItems = new ArrayList<>();

}
