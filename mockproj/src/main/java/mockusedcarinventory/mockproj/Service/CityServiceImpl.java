package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Entity.city;
import mockusedcarinventory.mockproj.Repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepo cityRepository;
    public List<Object[]> getCityInventoryCount()
    {
        List<Object[]> results=cityRepository.getuniquecitywithcountofinventory();
        return results;
//        return results.stream().map(result->Map.of(
//                "cityname",result[0],
//                "inventorycount",result[2]
//
//        )).collect(Collectors.toList());
    }
    public List<String> getallcountry()
    {
        return cityRepository.getallcountryfromcitytable();
    }

    public List<String> getallstatesfromcountry(String country) {
        return cityRepository.getallstatesfromcountry(country);
    }

    public List<Map<String,Object>> getcitydetailbypincode(String pincode)
    {

        return cityRepository.getcitydatafrompincode(pincode);
    }
    public List<String> getcitynamefromstatename(String statename ,String country)
    {

        return cityRepository.getallcitiesfromstates(statename,country);
    }
    public List<String> getpincodefromcityname(String cityname,String statename)
    {

        return cityRepository.getallpincodefromcity(cityname,statename);
    }
    public String createcity(city newcitydata)
    {
        List<Map<String,Object>> citydatacheck= getcitydetailbypincode(newcitydata.getPincode());
        if(citydatacheck.isEmpty()) {
            city citydata = new city();
            citydata.setPincode(newcitydata.getPincode());
            citydata.setCountry(newcitydata.getCountry());
            citydata.setStatename(newcitydata.getStatename());
            citydata.setCityname(newcitydata.getCityname());
            cityRepository.save(citydata);
            return "City created Successfully";
        }
        return "Already Present";
    }
    public List<city> getallcitydata()
    {
        return cityRepository.getallcitydetails();
    }
    public String Deletelocationbypincode(String pincode)
    {
        cityRepository.deleteById(pincode);
        return "Successsfully Deleted";
    }

}
