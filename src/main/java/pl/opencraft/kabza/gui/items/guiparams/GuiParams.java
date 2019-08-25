package pl.opencraft.kabza.gui.items.guiparams;

import org.bukkit.entity.Player;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public interface GuiParams<E> {

    Player getPlayer();
    E getE();
}
