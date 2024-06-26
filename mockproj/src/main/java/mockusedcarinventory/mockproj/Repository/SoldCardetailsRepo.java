package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.soldcardetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldCardetailsRepo extends JpaRepository<soldcardetails,Integer> {
    @Query(value="SELECT * FROM soldcardetails ",nativeQuery = true)
    public List<soldcardetails> getallcardetailswhicharesold();
}
