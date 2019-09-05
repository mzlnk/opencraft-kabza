package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class CreateBagType extends BaseCmdMethod {

    @Override
    public String description() {
        return "utworzenie nowego typu worka";
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
        sendSuccessMessage(params, "Pomyslnie utworzono nowy worek!");
    }
}
