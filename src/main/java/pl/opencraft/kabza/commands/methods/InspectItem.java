package pl.opencraft.kabza.commands.methods;

import com.google.gson.Gson;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public class InspectItem implements CmdMethod, CmdDescription {


    @Override
    public String description() {
        return "inspect nbt tags of ItemStack";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        plugin.nbtSerializer.serialize(params.item).ifPresent(dto -> {
            String json = new Gson().toJson(dto, ItemStackDto.class);
            params.player.sendMessage(json);
        });
    }
}
