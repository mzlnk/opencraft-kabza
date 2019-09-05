package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class RemoveAllowedItemLoreLine extends BaseCmdMethod {

    @Override
    public String description() {
        return "usuniecie linii danego Lore z danego typu przedmiotu";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);
        should(itemTypeExists, params);
        should(allowedItemTypeExists, params);
        should(allowedItemLoreNoIsValid, params);
        should(allowedItemLoreLineIsValid, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.findAllowedItemByType(params.itemType).ifPresent(bagTypeItem -> {
                bagTypeItem.getLores().get(params.loreNo).getLore().remove(params.lineNo);

                bagType.createOrUpdateAllowedItem(bagTypeItem);
                plugin.bagTypesService.createOrUpdateBagType(bagType);

                sendSuccessMessage(params, "Pomyslnie zaktualizowano usunieto linie z Lore");
            });
        });
    }

}
