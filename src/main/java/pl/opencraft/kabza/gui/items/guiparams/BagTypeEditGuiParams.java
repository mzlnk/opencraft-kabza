package pl.opencraft.kabza.gui.items.guiparams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import pl.opencraft.kabza.bags.repository.dto.BagType;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

@Getter
@AllArgsConstructor
public class BagTypeEditGuiParams implements GuiParams<BagType> {

    private Player player;
    private BagType e;

}
