package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.inventorydetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventorydetailsRepo extends JpaRepository<inventorydetails,Integer> {
    @Query(value="SELECT IFNULL(MAX(inventorynumber),0) FROM inventorydetails",nativeQuery = true)
    public int getValueofMaxInventoryNo();

    @Query(value="SELECT i.* "  + " FROM inventorydetails i " + "JOIN city c ON i.pincode = c.pincode " + " WHERE c.cityname=:cityname",nativeQuery = true)
    public List<inventorydetails> getallinventorydetailsofacity(@Param("cityname") String cityname);

    @Query(value="SELECT i.* " + " FROM inventorydetails i " + "JOIN city c ON i.pincode = c.pincode " + " WHERE c.pincode=:pincode",nativeQuery = true)
    public List<inventorydetails> getallinventorydetailfrompincode(@Param("pincode") String pincode);

    @Query(value = "SELECT inventorynumber,email,phonenumber FROM inventorydetails" ,nativeQuery=true)
    public List<Object[]> getcontactdetailsfrominventory();
    @Query(value="SELECT Count(*) FROM inventorydetails WHERE pincode=:pincode",nativeQuery = true)
    public Integer getallinventorycountbeforedeletecity(@Param("pincode") String pincode);


}
