package mockusedcarinventory.mockproj.Service;
import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Entity.inventorydetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InventoryService {
    public List<inventorydetails> getallinventorydetailsbycityname(String cityname);
    public List<inventorydetails> getallinventorydetailsbypincode(String pincode);
    public int maxValueOfInventoryNumber();
    public String CreateInventory(NewInventoryDetails newInventoryDetails);
    public inventorydetails getInventorydetailsByuniquecode(Integer inventorynumber );
    public List<inventorydetails> getAllInventoryInfo();
    public inventorydetails UpdatedInventorydetails(@Valid NewInventoryDetails newInventoryDetails, Integer inventorynumber);
    public String DeleteInventoryByUniqueCode(Integer inventorynumber);
    public List<Object[]> contactetailsofinventory();
    public Integer candeleteornotcity(String pincode);
     public List<Integer> getInventorynumber();
}
