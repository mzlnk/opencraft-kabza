package pl.opencraft.kabza.bags.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BagTypeItemLore {

    private List<String> lore = new ArrayList<>();

    public boolean equals(List<String> lore) {
        if(this.lore.size() != lore.size()) {
            return false;
        }

        for(int i = 0; i < this.lore.size(); i++) {
            if(!this.lore.get(i).equals(lore.get(i))) {
                return false;
            }
        }

        return true;
    }

}
