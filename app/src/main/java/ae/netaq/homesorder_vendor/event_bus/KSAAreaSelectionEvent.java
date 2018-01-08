package ae.netaq.homesorder_vendor.event_bus;

import java.util.List;

import ae.netaq.homesorder_vendor.models.Country;

/**
 * Created by sabih on 04-Jan-18.
 */

public class KSAAreaSelectionEvent {


    private final List<Country.State> selectedStates;
    private final Country ksaRegion;

    public KSAAreaSelectionEvent(Country ksaRegion, List<Country.State> selectedStates) {

        this.ksaRegion = ksaRegion;
        this.selectedStates = selectedStates;
    }

    public List<Country.State> getSelectedStates() {
        return selectedStates;
    }

    public Country getKsaRegion() {
        return ksaRegion;
    }
}
