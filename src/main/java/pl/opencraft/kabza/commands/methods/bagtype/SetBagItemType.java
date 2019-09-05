package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class SetBagItemType extends BaseCmdMethod {

    @Override
    public String description() {
        return "zmiana przedmiotu reprezentujacego worek";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);
        should(itemTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.setBagItemType(params.itemType);
            plugin.bagTypesService.createOrUpdateBagType(bagType);

            sendSuccessMessage(params, "Pomyslnie zmieniono przedmiot");
        });
    }

}
