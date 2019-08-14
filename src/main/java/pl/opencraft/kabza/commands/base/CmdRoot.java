package pl.opencraft.kabza.commands.base;

import org.bukkit.command.CommandSender;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class CmdRoot extends CmdNode {

    public CmdRoot(String token) {
        super(token);
    }

    @Override
    public CmdRoot setMethod(CmdMethod cmdMethod) {
        return (CmdRoot) super.setMethod(cmdMethod);
    }

    @Override
    public CmdRoot subCmds(CmdNode... cmds) {
        return (CmdRoot) super.subCmds(cmds);
    }

    public void onCommand(CommandSender sender, String label, String[] args) {
        cmd = "";
        CmdMethodParams params = new CmdMethodParams(sender);
        try {
            executeCommand(params, label, args);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
    }

}
