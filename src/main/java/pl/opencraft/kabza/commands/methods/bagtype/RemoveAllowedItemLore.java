package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class RemoveAllowedItemLore extends BaseCmdMethod {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_REMOVE_ALLOWED_ITEM_LORE);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);
        should(itemTypeExists, params);
        should(allowedItemTypeExists, params);
        should(allowedItemLoreNoIsValid, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            bagType.findAllowedItemByType(params.itemType).ifPresent(bagTypeItem -> {
                bagTypeItem.getLores().remove(params.loreNo);

                bagType.createOrUpdateAllowedItem(bagTypeItem);
                plugin.bagTypesService.createOrUpdateBagType(bagType);

                sendSuccessMessage(params, plugin.messages.get(MessageEnum.CMD_SUCCESS_REMOVED_ALLOWED_ITEM_LORE));
            });
        });

    }

}
