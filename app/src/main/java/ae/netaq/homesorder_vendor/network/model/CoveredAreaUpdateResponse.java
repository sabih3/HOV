package ae.netaq.homesorder_vendor.network.model;

import java.util.List;

public class CoveredAreaUpdateResponse
{
    public int code;
    public String message;
    public List<Coveredarea> coveredarea;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Coveredarea> getCoveredarea() {
        return coveredarea;
    }

    public class Area
    {
        public int areaID;
        public String areaNameEN;
        public String areaNameAR;
        public String areaCode;
        public int stateID;
        public int countryID;
    }

    public class State
    {
        public int stateID;
        public int countryID;
        public String stateNameEN;
        public String stateNameAR;
        public String stateCode;
        public List<Area> areas;
    }

    public class Coveredarea
    {
        public int countryID;
        public String countryCode;
        public String countryNameEN;
        public String countryNameAR;
        public List<State> states;
    }


}