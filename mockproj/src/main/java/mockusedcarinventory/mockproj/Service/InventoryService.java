package mockusedcarinventory.mockproj.Service;
import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Entity.Inventorydetails;

import java.util.List;

public interface InventoryService {
    public List<Inventorydetails> inventoryDetailsOfCity(String cityname);
    public List<Inventorydetails> inventorydetailsFromPincode(String pincode);
    public int maxValueOfInventoryNumber();
    public String createInventory(NewInventoryDetails newInventoryDetails);
    public Inventorydetails inventoryDetailsByInventoryNumber(Integer inventorynumber );
    public List<Inventorydetails> allInventoryDetails();
    public Inventorydetails updatedInventorydetails(@Valid NewInventoryDetails newInventoryDetails, Integer inventorynumber);
    public String deleteInventoryByInventoryNumber(Integer inventorynumber);
    public List<Object[]> contactDetailsOfInventories();
    public Integer countOfInventoryInPincode(String pincode);
    public List<Integer> allInventoryNumber();
    public String pincodeOfInventoryNumber(Integer inventorynumber);
    public Integer candeleteornotcity(String pincode);
}
