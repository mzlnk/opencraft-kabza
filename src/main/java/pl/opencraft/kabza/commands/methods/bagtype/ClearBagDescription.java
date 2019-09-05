package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class ClearBagDescription extends BaseCmdMethod {

    @Override
    public String description() {
        return "usuniecie opisu worka";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.getBagDescription().clear();
            plugin.bagTypesService.createOrUpdateBagType(bagType);

            sendSuccessMessage(params, "Pomyslnie usunieto opis worka");
        });
    }

}
