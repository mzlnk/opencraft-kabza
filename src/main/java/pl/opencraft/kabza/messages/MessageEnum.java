package pl.opencraft.kabza.messages;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public enum MessageEnum {

    MISSING_MESSAGE,

    CMD_ERROR_LORE_LINE_NOT_VALID,
    CMD_ERROR_LORE_NO_NOT_VALID,
    CMD_ERROR_NAME_NO_NOT_VALID,
    CMD_ERROR_ITEM_TYPE_NOT_FOUND,
    CMD_ERROR_DESCRIPTION_LINE_NOT_VALID,
    CMD_ERROR_BAG_TYPE_NOT_FOUND,
    CMD_ERROR_BAG_TYPE_EXIST,
    CMD_ERROR_PLAYER_HAS_NO_ADMIN_PERMISSION,
    CMD_ERROR_PLAYER_HAS_NO_BAG_IN_HAND,
    CMD_ERROR_PLAYER_HAS_ITEM_IN_HAND,
    CMD_ERROR_PLAYER_HAS_NO_ITEM_IN_HAND,

    CMD_INFO_ADD_ALLOWED_ITEM_LORE_LINE,
    CMD_INFO_ADD_ALLOWED_ITEM_NAME,
    CMD_INFO_ADD_BAG_DESCRIPTION_LINE,
    CMD_INFO_CLEAR_ALLOWED_ITEM_NAMES,
    CMD_INFO_CLEAR_ALLOWED_ITEM_LORES,
    CMD_INFO_CLEAR_BAG_DESCRIPTION,
    CMD_INFO_CREATE_BAG_TYPE,
    CMD_INFO_INSPECT_BAG_TYPE,
    CMD_INFO_INSPECT_ALLOWED_ITEM,
    CMD_INFO_REMOVE_ALLOWED_ITEM_LORE,
    CMD_INFO_REMOVE_ALLOWED_ITEM_LORE_LINE,
    CMD_INFO_REMOVE_ALLOWD_ITEM_NAME,
    CMD_INFO_REMOVE_BAG_DESCRIPTION_LINE,
    CMD_INFO_SET_ALL_ITEMS_ALLOWED,
    CMD_INFO_SET_ALLOWED_ITEM_ALL_LORES_ALLOWED,
    CMD_INFO_SET_ALLOWED_ITEM_ALL_NAMES_ALLOWED,
    CMD_INFO_SET_ALLOWED_ITEM_NO_NAME_ALLOWED,
    CMD_INFO_SET_ALLOWED_ITEM_NO_LORE_ALLOWED,
    CMD_INFO_SET_BAG_ITEM_TYPE,
    CMD_INFO_SET_BAG_NAME,
    CMD_INFO_SET_CRAFTING_ENABLED,
    CMD_INFO_SET_SHAPELESS_CRAFTING,

    CMD_INFO_GIVE_BAG,
    CMD_INFO_INSPECT_BAG,
    CMD_INFO_RELOAD,

    BAG_INFO_CAPACITY,
    BAG_INFO_OCCUPIED_SLOTS;


}
