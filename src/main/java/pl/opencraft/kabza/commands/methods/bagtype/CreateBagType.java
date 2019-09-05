package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class CreateBagType extends BaseCmdMethod {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_CREATE_BAG_TYPE);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeNotExist, params);

        BagType bagType = BagType.builder()
                .id(params.bagTypeId)
                .bagName("bag - " + params.bagTypeId)
                .build();

        plugin.bagTypesService.createOrUpdateBagType(bagType);
        sendSuccessMessage(params, plugin.messages.get(MessageEnum.CMD_SUCCESS_CREATED_BAG_TYPE));
    }
}
