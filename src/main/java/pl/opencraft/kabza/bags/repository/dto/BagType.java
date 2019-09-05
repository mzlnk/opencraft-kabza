package pl.opencraft.kabza.bags.repository.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
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

    public Optional<BagTypeItem> findAllowedItemByType(Material type) {
        for(BagTypeItem item : allowedItems) {
            if(item.getType().equals(type)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public void createOrUpdateAllowedItem(BagTypeItem bagTypeItem) {
        if(allowedItems.stream().anyMatch(item -> item.getType().equals(bagTypeItem.getType()))) {
            for(int i = 0; i < allowedItems.size(); i++) {
                if(allowedItems.get(i).getType().equals(bagTypeItem.getType())) {
                    allowedItems.remove(i);
                    break;
                }
            }
        }

        allowedItems.add(bagTypeItem);
    }

    public void removeAllowedItem(Material itemType) {
        for(int i = 0; i < allowedItems.size(); i++) {
            if(allowedItems.get(i).getType().equals(itemType)) {
                allowedItems.remove(i);
                break;
            }
        }
    }

}
