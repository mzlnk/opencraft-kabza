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

    abstract boolean areParamsValid(CmdMethodParams params);
    abstract String failMessage();

}
