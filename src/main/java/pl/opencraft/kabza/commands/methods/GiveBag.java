package pl.opencraft.kabza.commands.methods;

import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class GiveBag implements CmdMethod, CmdDescription {


    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_GIVE_BAG);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(playerHasNoItemInHand, params);

        Bag bag = plugin.bagsService.createNewBag(params.bagTypeId);
        params.player.getInventory().setItemInMainHand(bag.toItemStack());
    }
}
