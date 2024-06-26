package mockusedcarinventory.mockproj.Repository;

import lombok.Value;
import mockusedcarinventory.mockproj.Entity.city;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CityRepo extends JpaRepository<city,String> {
    @Query(value="SELECT c.cityname AS cityname,c.statename AS statename ,COUNT(i.inventorynumber) AS inventorycount " +
            "FROM city c  LEFT JOIN inventorydetails i  ON c.pincode=i.pincode " +
            "GROUP BY c.cityname,c.statename " +
            "HAVING (COUNT(i.inventorynumber))>0;",nativeQuery = true)
    List<Object[]> getuniquecitywithcountofinventory();
    @Query(value = "SELECT distinct(country) FROM city" ,nativeQuery=true)
    public List<String> getallcountryfromcitytable();
    @Query(value = "SELECT distinct(c.statename) FROM city c WHERE c.country=:country" ,nativeQuery=true)
    public List<String> getallstatesfromcountry(@Param("country") String country);
    @Query(value = "SELECT distinct(c.cityname) FROM city c WHERE (c.statename=:statename AND c.country=:country)" ,nativeQuery=true)
    public List<String> getallcitiesfromstates(@Param("statename") String statename,@Param("country") String country);
    @Query(value = "SELECT c.pincode FROM city c WHERE (c.cityname=:cityname AND c.statename=:statename)" ,nativeQuery=true)
    public List<String> getallpincodefromcity(@Param("cityname") String cityname,@Param("statename") String statename);
    @Query(value = "SELECT c.cityname,c.statename,c.country FROM city c WHERE c.pincode=:pincode" ,nativeQuery=true)
    public List<Map<String,Object>> getcitydatafrompincode(@Param("pincode") String pincode);
    @Query(value = "SELECT * FROM city ",nativeQuery = true)
    public List<city> getallcitydetails();




}
