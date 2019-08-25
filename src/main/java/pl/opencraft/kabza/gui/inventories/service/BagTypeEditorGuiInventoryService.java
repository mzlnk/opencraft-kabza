package pl.opencraft.kabza.gui.inventories.service;

import org.bukkit.entity.Player;
import pl.opencraft.kabza.bags.repository.dto.BagType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public class BagTypeEditorGuiInventoryService {

    private Map<UUID, GuiInventoryActivityInfo<BagType>> playersInGui = new HashMap<>();

    public static BagTypeEditorGuiInventoryService init() {
        BagTypeEditorGuiInventoryService service = new BagTypeEditorGuiInventoryService();
        return service;
    }

    public void putPlayerInGuiInventory(GuiInventoryActivityInfo<BagType> activityInfo) {
        playersInGui.put(activityInfo.getPlayerUuid(), activityInfo);

        Player player = plugin.getServer().getPlayer(activityInfo.getPlayerUuid());
        //plugin.debugLogger.info("Player - " + player.getName() + " - entered module gui inventory: " + activityInfo.getModuleGuiInventory().getModuleGuiInventoryType().name());
    }

    public void removePlayerFromGuiInventory(UUID uuid) {
        playersInGui.remove(uuid);

        Player player = plugin.getServer().getPlayer(uuid);
        //plugin.debugLogger.info("Player - " + player.getName() + " - left module gui inventory");
    }

    public boolean isPlayerInGuiInventory(UUID uuid) {
        return playersInGui.get(uuid) != null;
    }

    public GuiInventoryActivityInfo<BagType> getGuiInventoryActivityInfo(UUID playerUuid) {
        return playersInGui.get(playerUuid);
    }

    public boolean isSomeoneUseE(String id) {
        for(GuiInventoryActivityInfo<BagType> info : playersInGui.values()) {
            if(info.getE().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Map<UUID, GuiInventoryActivityInfo<BagType>> getPlayersMap() {
        return playersInGui;
    }

}
