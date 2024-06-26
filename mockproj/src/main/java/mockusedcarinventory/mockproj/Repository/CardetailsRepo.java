package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.cardetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardetailsRepo extends JpaRepository<cardetails,Integer> {
    @Query(value="SELECT IFNULL(MAX(saleno),0) FROM cardetails" ,nativeQuery = true)
    public int getValueofmaxsaleno();
    @Query(value="SELECT * FROM cardetails WHERE inventorynumber=:inventorynumber",nativeQuery = true)
    public List<cardetails> getallcardetailswithuniquecode(@Param("inventorynumber") Integer inventorynumber);

    @Query(value="SELECT Count(*) FROM cardetails WHERE inventorynumber=:inventorynumber",nativeQuery = true)
    public Integer getallcarcountbeforedeletefrominventorynumber(@Param("inventorynumber") Integer inventorynumber);
}
