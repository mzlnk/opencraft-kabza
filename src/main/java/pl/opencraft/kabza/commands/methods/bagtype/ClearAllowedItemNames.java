package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class ClearAllowedItemNames extends BaseCmdMethod {

    @Override
    public String description() {
        return "usuniecie wszystkich dozwolonych nazw z danego typu przedmiotu";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);
        should(itemTypeExists, params);
        should(allowedItemTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.findAllowedItemByType(params.itemType).ifPresent(bagTypeItem -> {
                bagTypeItem.getNames().clear();

                bagType.createOrUpdateAllowedItem(bagTypeItem);
                plugin.bagTypesService.createOrUpdateBagType(bagType);

                sendSuccessMessage(params, "Pomyslnie usunieto dopuszczalne nazwy z danego typu przedmiotu");
            });
        });
    }
}
