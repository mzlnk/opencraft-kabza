package pl.opencraft.kabza.commands.base;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import pl.opencraft.kabza.bags.repository.dto.BagType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.bukkit.ChatColor.*;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.utils.ArgsUtil.restArgs;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class CmdNode {

    public static String cmd;

    public String token;
    public List<CmdNode> children = new ArrayList<>();
    public CmdMethod cmdMethod = new DefaultCmdMethod();

    public CmdNode(String token) {
        this.token = token;
    }

    public CmdNode setMethod(CmdMethod cmdMethod) {
        this.cmdMethod = cmdMethod;
        return this;
    }

    public CmdNode subCmds(CmdNode... cmds) {
        for (CmdNode cmd : cmds) {
            children.add(cmd);
        }
        return this;
    }

    public List<String> onTabComplete(String[] args) {
        List<String> prompts = new ArrayList<>();
        if (args.length > 1) {
            for (CmdNode child : children) {
                if (child.token.equals(args[0])) {
                    return child.onTabComplete(restArgs(args));
                }
            }
            for (CmdNode child : children) {
                if (child.token.startsWith("<")) {
                    return child.onTabComplete(restArgs(args));
                }
            }
            return prompts;
        }
        if (args.length == 1) {
            for (CmdNode child : children) {
                if(child.token.equals("<bag_type_id>")) {
                    prompts.addAll(this.getBagTypeIdsList(args[0]));
                    continue;
                }

                if(child.token.equals("<item_type>")) {
                    prompts.addAll(this.getBagTypeIdsList(args[0]));
                    continue;
                }

                String prefix = (args[0] == null ? "" : args[0].toLowerCase());
                if (child.token.toLowerCase().startsWith(prefix)) {
                    prompts.add(child.token);
                }
            }
        }
        return prompts;
    }

    public void executeCommand(CmdMethodParams params, String label, String[] args) {
        cmd = cmd + token + ' ';

        if(token.equals("<bag_type_id>")) {
            params.setBagTypeId(label);
        }
        if(token.equals("<bag_name>")) {
            params.setBagName(label);
        }
        if(token.equals("<item_type>")) {
            params.equals(Material.getMaterial(label));
        }
        if(token.equals("<flag>")) {
            params.setFlag(Boolean.parseBoolean(label));
        }
        if(token.equals("<line_no>")) {
            params.setLineNo(NumberUtils.toInt(label, 0) - 1);
        }
        if(token.equals("<name_no>")) {
            params.setNameNo(NumberUtils.toInt(label, 0) - 1);
        }
        if(token.equals("<lore_no")) {
            params.setLoreNo(NumberUtils.toInt(label, 0) - 1);
        }
        if(token.equals("<line>")) {
            params.setLine(label);
        }

        cmdMethod.executeCommand(params);
        if (args.length > 0) {
            for (CmdNode child : children) {
                if (child.token.equals(args[0])) {
                    child.executeCommand(params, args[0], restArgs(args));
                    return;
                }
            }
            for (CmdNode child : children) {
                if (child.token.startsWith("<")) {
                    child.executeCommand(params, args[0], restArgs(args));
                    return;
                }
            }
            params.sender.sendMessage(PREFIX + RED + "Nieprawidlowy argument: " + YELLOW + args[0] + RED + '.' +
                    '\n' + GREEN + "Dostepne autouzupelnianie klawiszem TAB!");
            return;
        }
        if (!children.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX).append(RED).append("Komenda jest niekompletna!");
            sb.append('\n').append(WHITE).append("Uzycie:");
            for (CmdNode child : children) {
                sb.append('\n').append(BLUE).append('/').append(cmd).append(child.token);
                if (!child.children.isEmpty()) {
                    sb.append(" ...");
                }
                if (child.cmdMethod instanceof CmdDescription) {
                    sb.append(WHITE).append(" - ").append(((CmdDescription) child.cmdMethod).description());
                }
            }
            sb.append('\n').append(GREEN).append("Dostepne autouzupelnianie klawiszem TAB!");
            params.sender.sendMessage(sb.toString());
        }
    }

    private List<String> getBagTypeIdsList(String arg) {
        String prefix = (arg == null ? "" : arg.toLowerCase());
        return plugin.bagTypesService.findAll().stream()
                .map(BagType::getId)
                .filter(n -> n.toLowerCase().contains(prefix))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<String> getItemList(String arg) {
        String prefix = (arg == null ? "" : arg.toLowerCase());
        return Arrays.stream(Material.values())
                .map(Material::name)
                .filter(n -> n.toLowerCase().contains(prefix))
                .sorted()
                .collect(Collectors.toList());
    }

}
