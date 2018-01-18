package ae.netaq.homesorder_vendor.event_bus;

import java.util.List;

import ae.netaq.homesorder_vendor.models.Country;

/**
 * Created by sabih on 04-Jan-18.
 */

public class UAEAreasSelectedEvent {
    private final List<Country.State> selectedStates;
    private final Country uaeRegion;

    public UAEAreasSelectedEvent(Country uaeRegion, List<Country.State> selectedStates) {
        this.uaeRegion = uaeRegion;
        this.selectedStates = selectedStates;
    }

    public List<Country.State> getSelectedStates() {
        return selectedStates;
    }

    public Country getUaeRegion() {
        return uaeRegion;
    }
}
