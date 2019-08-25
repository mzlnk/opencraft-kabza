package pl.opencraft.kabza.bags.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

@Data
@Builder
@AllArgsConstructor
public class BagTypeItem {

    private Material type;

    private boolean allNamesAllowed;
    private boolean allLoresAllowed;

    private boolean noNameAllowed;
    private boolean noLoreAllowed;

    private List<String> names;
    private List<BagTypeItemLore> lores;

    public boolean isItemApplicable(ItemStack itemStack) {
        if(!itemStack.getType().equals(type)) {
            return false;
        }

        if(allNamesAllowed && allLoresAllowed) {
            return true;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta == null) {
            return false;
        }

        if(!allNamesAllowed) {
            if(!noNameAllowed && !itemMeta.hasDisplayName()) {
                return false;
            }
            if(itemMeta.hasDisplayName() && !names.contains(itemMeta.getDisplayName())) {
                return false;
            }
        }

        if(!allLoresAllowed) {
            if(!noLoreAllowed && !itemMeta.hasLore()) {
                return false;
            }
            if(itemMeta.hasLore() && lores.stream().noneMatch(itemLore -> itemLore.equals(itemMeta.getLore()))) {
                return false;
            }
        }

        return true;
    }

}
