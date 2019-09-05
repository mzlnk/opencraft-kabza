package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public abstract class CmdParamsValidator {

    public static void should(boolean necessaryCondition, String failMessage) {
        if (necessaryCondition) {
            return;
        }
        throw new IllegalArgumentException(failMessage);
    }

    public static void should(CmdParamsValidator validator, CmdMethodParams params) {
        should(validator, params, validator.failMessage());
    }

    public static void should(CmdParamsValidator validator, CmdMethodParams params, String failMessage) {
        if (validator.areParamsValid(params)) {
            return;
        }
        throw new IllegalArgumentException(failMessage);
    }

    public static PlayerHasAdminPermission playerHasAdminPermission = new PlayerHasAdminPermission();
    public static PlayerHasBagInHand playerHasBagInHand = new PlayerHasBagInHand();
    public static PlayerHasNoItemInHand playerHasNoItemInHand = new PlayerHasNoItemInHand();
    public static BagTypeExists bagTypeExists = new BagTypeExists();
    public static AllowedItemTypeExists allowedItemTypeExists = new AllowedItemTypeExists();
    public static ItemTypeExists itemTypeExists = new ItemTypeExists();
    public static PlayerHasItemInHand playerHasItemInHand = new PlayerHasItemInHand();
    public static BagTypeNotExist bagTypeNotExist = new BagTypeNotExist();

    abstract boolean areParamsValid(CmdMethodParams params);
    abstract String failMessage();

}
