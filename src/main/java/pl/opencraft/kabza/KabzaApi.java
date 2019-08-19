package pl.opencraft.kabza;

import pl.opencraft.kabza.bags.service.BagTypesService;
import pl.opencraft.kabza.bags.service.BagsService;

public interface KabzaApi {

    BagsService getBagsService();
    BagTypesService getBagTypesService();

}
