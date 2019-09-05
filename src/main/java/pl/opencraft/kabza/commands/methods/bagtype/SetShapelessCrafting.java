package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class SetShapelessCrafting extends BaseCmdMethod {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_SET_SHAPELESS_CRAFTING);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.setShapelessCrafting(params.flag);
            plugin.bagTypesService.createOrUpdateBagType(bagType);

            sendSuccessMessage(params, plugin.messages.get(MessageEnum.CMD_SUCCESS_SET_SHAPELESS_CRAFTING));
        });

    }
}
