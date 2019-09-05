package pl.opencraft.kabza.commands.methods;

import org.bukkit.ChatColor;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.playerHasAdminPermission;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.should;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class Reload implements CmdMethod, CmdDescription {
    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_RELOAD);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);

        plugin.bagsService.reload();
        plugin.bagTypesService.reload();
        params.player.sendMessage(PREFIX + ChatColor.GREEN + "Plugin zostal zaladowany ponownie");
    }
}
