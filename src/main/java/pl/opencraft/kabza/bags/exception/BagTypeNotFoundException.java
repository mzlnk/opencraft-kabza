package pl.opencraft.kabza.bags.exception;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class BagTypeNotFoundException extends RuntimeException {

    public BagTypeNotFoundException() {
        super();
    }

    public BagTypeNotFoundException(String message) {
        super(message);
    }

}
