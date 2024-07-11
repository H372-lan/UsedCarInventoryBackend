package mockusedcarinventory.mockproj.Repository;

import mockusedcarinventory.mockproj.Entity.Soldcardetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldCardetailsRepo extends JpaRepository<Soldcardetails,Integer> {

}
