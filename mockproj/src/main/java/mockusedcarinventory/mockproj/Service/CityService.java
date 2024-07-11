package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Entity.City;

import java.util.List;
import java.util.Map;

public interface CityService {
    public List<Object[]> cityDataWithInventoryCount();
    public List<String> allCountry();
    public List<String> allStatesInCountry(String country);
    public List<Map<String,Object>> cityDetailsByPincode(String pincode);
    public List<String> cityNameFromStatename(String statename, String country);
    public List<String> allPincodeofCity(String cityname, String statename);
    public String CreateCity(City newcitydata);
    public List<City> allCityData();
    public String deleteLocationByPincode(String pincode);
}
