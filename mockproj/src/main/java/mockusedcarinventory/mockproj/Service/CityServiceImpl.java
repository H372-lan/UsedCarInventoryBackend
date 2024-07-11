package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Entity.City;
import mockusedcarinventory.mockproj.Repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepo cityRepository;
    public List<Object[]> cityDataWithInventoryCount()
    {
        List<Object[]> results=cityRepository.cityDataWithInventoryCount();
        return results;
    }
    public List<String> allCountry ()
    {
        return cityRepository.allCountry();
    }

    public List<String> allStatesInCountry(String country) {
        return cityRepository.allStatesFromCountry(country);
    }

    public List<Map<String,Object>> cityDetailsByPincode (String pincode)
    {

        return cityRepository.cityDataFromPincode(pincode);
    }
    public List<String> cityNameFromStatename(String statename , String country)
    {

        return cityRepository.allCitiesNameFromState(statename,country);
    }
    public List<String> allPincodeofCity(String cityname, String statename)
    {

        return cityRepository.allPincodeOfCity(cityname,statename);
    }
    public String CreateCity(City newcitydata)
    {
        List<Map<String,Object>> citydatacheck= cityDetailsByPincode(newcitydata.getPincode());
        if(citydatacheck.isEmpty()) {
            City citydata = new City();
            citydata.setPincode(newcitydata.getPincode());
            citydata.setCountry(newcitydata.getCountry());
            citydata.setStateName(newcitydata.getStateName());
            citydata.setCityName(newcitydata.getCityName());
            cityRepository.save(citydata);
            return "City created Successfully";
        }
        return "Already Present";
    }
    public List<City> allCityData ()
    {
        return cityRepository.findAll();
    }
    public String deleteLocationByPincode(String pincode)
    {
        cityRepository.deleteById(pincode);
        return "Successsfully Deleted";
    }

}
