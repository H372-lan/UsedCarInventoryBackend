package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.Cardetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardetailsRepo extends JpaRepository<Cardetails,Integer> {
    @Query(value="SELECT IFNULL(MAX(sale_no),0) FROM car_details" ,nativeQuery = true)
    public int maxValueofSaleNo();
    @Query(value="SELECT * FROM car_details WHERE inventory_number=:inventorynumber",nativeQuery = true)
    public List<Cardetails> carDetailsFromInventoryNumber(@Param("inventorynumber") Integer inventorynumber);

    @Query(value="SELECT Count(*) FROM car_details WHERE inventory_number=:inventorynumber",nativeQuery = true)
    public Integer carCountInaInventory(@Param("inventorynumber") Integer inventorynumber);
}
