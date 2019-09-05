package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class AddBagDescriptionLine extends BaseCmdMethod {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_ADD_BAG_DESCRIPTION_LINE);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);
        should(bagDescriptionLineIsValid, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.getBagDescription().add(params.lineNo, params.line);
            plugin.bagTypesService.createOrUpdateBagType(bagType);

            sendSuccessMessage(params, plugin.messages.get(MessageEnum.CMD_SUCCESS_ADDED_BAG_DESCRIPTION_LINE));
        });
    }
}
