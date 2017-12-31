package ae.netaq.homesorder_vendor.models;

import java.util.List;

/**
 * Created by sabih on 30-Dec-17.
 */

public class Areas {
    private String countryNameEN;
    private String countryNameAR;
    private int countryID;
    private String countryCode;
    private List<State> states;


    public String getCountryNameEN() {
        return countryNameEN;
    }

    public String getCountryNameAR() {
        return countryNameAR;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<State> getStates() {
        return states;
    }

    public class State {
        private String stateNameEN;
        private String stateNameAR;
        private int stateID;
        private String stateCode;
        private List<Area> areas;

        public String getStateNameEN() {
            return stateNameEN;
        }

        public String getStateNameAR() {
            return stateNameAR;
        }

        public int getStateID() {
            return stateID;
        }

        public String getStateCode() {
            return stateCode;
        }

        public List<Area> getAreas() {
            return areas;
        }

        public class Area {
            private String areaNameEN;
            private String areaNameAR;
            private String areaID;
            private String areaCode;


            public String getAreaNameEN() {
                return areaNameEN;
            }

            public String getAreaNameAR() {
                return areaNameAR;
            }

            public String getAreaID() {
                return areaID;
            }

            public String getAreaCode() {
                return areaCode;
            }
        }
    }
}
