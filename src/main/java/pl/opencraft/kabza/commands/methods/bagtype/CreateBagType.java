package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.bagTypeNotExist;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.playerHasAdminPermission;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.should;
import static pl.opencraft.KabzaPlugin.plugin;
import static org.bukkit.ChatColor.GREEN;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class CreateBagType implements CmdMethod, CmdDescription {

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
        params.sender.sendMessage(PREFIX + GREEN + "Pomyslnie utworzono nowy worek!");
    }
}
