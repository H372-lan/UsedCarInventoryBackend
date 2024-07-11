package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.Inventorydetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventorydetailsRepo extends JpaRepository<Inventorydetails,Integer> {
    @Query(value="SELECT IFNULL(MAX(inventory_number),0) FROM inventory_details",nativeQuery = true)
    public int maxInventoryNumber();

    @Query(value="SELECT i.* "  + " FROM inventory_details i " + "JOIN City c ON i.pincode = c.pincode " + " WHERE c.city_name=:cityname",nativeQuery = true)
    public List<Inventorydetails> inventoryDetailsOfCity(@Param("cityname") String cityname);

    @Query(value="SELECT i.* " + " FROM inventory_details i " + "JOIN City c ON i.pincode = c.pincode " + " WHERE c.pincode=:pincode",nativeQuery = true)
    public List<Inventorydetails> inventoryDetailsFromPincode(@Param("pincode") String pincode);

    @Query(value = "SELECT inventory_number,email,phonenumber FROM inventory_details" ,nativeQuery=true)
    public List<Object[]> contactDetailsOfInventories();
    @Query(value="SELECT Count(*) FROM inventory_details WHERE pincode=:pincode",nativeQuery = true)
    public Integer countOfInventoryInPincode(@Param("pincode") String pincode);
    @Query(value = "SELECT inventory_number FROM inventory_details",nativeQuery = true)
    public List<Integer> allInventoryNumbers ();
    @Query(value = "SELECT pincode FROM inventory_details WHERE inventory_number=:inventorynumber",nativeQuery = true)
    public String pincodeFromInventoryNumber (@Param("inventorynumber") Integer inventorynumber);


}
