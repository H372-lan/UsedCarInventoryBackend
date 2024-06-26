package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Entity.city;

import java.util.List;
import java.util.Map;

public interface CityService {
    public List<Object[]> getCityInventoryCount();
    public List<String> getallcountry();
    public List<String> getallstatesfromcountry(String country);
    public List<Map<String,Object>> getcitydetailbypincode(String pincode);
    public List<String> getcitynamefromstatename(String statename,String country);
    public List<String> getpincodefromcityname(String cityname,String statename);
    public String createcity(city newcitydata);
    public List<city> getallcitydata();
    public String Deletelocationbypincode(String pincode);
}
