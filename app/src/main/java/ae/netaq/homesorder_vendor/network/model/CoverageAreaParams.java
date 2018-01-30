package ae.netaq.homesorder_vendor.network.model;


import java.util.List;

public class CoverageAreaParams
{
    public String countryNameEN;
    public String countryNameAR;
    public int countryID;
    public String countryCode;
    public List<State> states;

    public String getCountryNameEN() {
        return countryNameEN;
    }

    public void setCountryNameEN(String countryNameEN) {
        this.countryNameEN = countryNameEN;
    }

    public String getCountryNameAR() {
        return countryNameAR;
    }

    public void setCountryNameAR(String countryNameAR) {
        this.countryNameAR = countryNameAR;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public static class Area
    {
        public String areaNameEN;
        public String areaNameAR;
        public int areaID;
        public String areaCode;
        public String stateID;

        public String getAreaNameEN() {
            return areaNameEN;
        }

        public void setAreaNameEN(String areaNameEN) {
            this.areaNameEN = areaNameEN;
        }

        public String getAreaNameAR() {
            return areaNameAR;
        }

        public void setAreaNameAR(String areaNameAR) {
            this.areaNameAR = areaNameAR;
        }

        public int getAreaID() {
            return areaID;
        }

        public void setAreaID(int areaID) {
            this.areaID = areaID;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getStateID() {
            return stateID;
        }

        public void setStateID(String stateID) {
            this.stateID = stateID;
        }
    }

    public static class State
    {
        public String stateNameEN;
        public String stateNameAR;
        public int stateID;
        public String stateCode;
        public int countryID;
        public List<Area> areas;

        public State() {
        }

        public String getStateNameEN() {
            return stateNameEN;
        }

        public void setStateNameEN(String stateNameEN) {
            this.stateNameEN = stateNameEN;
        }

        public String getStateNameAR() {
            return stateNameAR;
        }

        public void setStateNameAR(String stateNameAR) {
            this.stateNameAR = stateNameAR;
        }

        public int getStateID() {
            return stateID;
        }

        public void setStateID(int stateID) {
            this.stateID = stateID;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public int getCountryID() {
            return countryID;
        }

        public void setCountryID(int countryID) {
            this.countryID = countryID;
        }

        public List<Area> getAreas() {
            return areas;
        }

        public void setAreas(List<Area> areas) {
            this.areas = areas;
        }
    }
}
