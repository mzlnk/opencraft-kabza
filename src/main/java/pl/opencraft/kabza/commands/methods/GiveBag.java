package pl.opencraft.kabza.commands.methods;

import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class GiveBag implements CmdMethod, CmdDescription {


    @Override
    public String description() {
        return "otrzymanie specjalnego worka";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        Bag bag = plugin.bagsService.createNewBag(params.bagTypeId);

        params.player.getInventory().setItemInMainHand(bag.toItemStack());
    }
}
