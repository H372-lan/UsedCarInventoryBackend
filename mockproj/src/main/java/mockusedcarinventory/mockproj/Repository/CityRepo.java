package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CityRepo extends JpaRepository<City,String> {
    @Query(value="SELECT c.city_name AS cityname,c.state_name AS statename ,COUNT(i.inventory_number) AS inventorycount " +
            "FROM City c  LEFT JOIN inventory_details i  ON c.pincode=i.pincode " +
            "GROUP BY c.city_name,c.state_name " +
            "HAVING (COUNT(i.inventory_number))>0;",nativeQuery = true)
    List<Object[]> cityDataWithInventoryCount();
    @Query(value = "SELECT distinct(country) FROM City" ,nativeQuery=true)
    public List<String> allCountry();
    @Query(value = "SELECT distinct(c.state_name) FROM City c WHERE c.country=:country" ,nativeQuery=true)
    public List<String> allStatesFromCountry (@Param("country") String country);
    @Query(value = "SELECT distinct(c.city_name) FROM City c WHERE (c.state_name=:statename AND c.country=:country)" ,nativeQuery=true)
    public List<String> allCitiesNameFromState(@Param("statename") String statename, @Param("country") String country);
    @Query(value = "SELECT c.pincode FROM City c WHERE (c.city_name=:cityname AND c.state_name=:statename)" ,nativeQuery=true)
    public List<String> allPincodeOfCity (@Param("cityname") String cityname, @Param("statename") String statename);
    @Query(value = "SELECT c.city_name,c.state_name,c.country FROM City c WHERE c.pincode=:pincode" ,nativeQuery=true)
    public List<Map<String,Object>> cityDataFromPincode (@Param("pincode") String pincode);





}
