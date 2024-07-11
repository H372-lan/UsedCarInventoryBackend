package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Entity.City;
import mockusedcarinventory.mockproj.Entity.Inventorydetails;
import mockusedcarinventory.mockproj.Repository.CityRepo;
import mockusedcarinventory.mockproj.Repository.InventorydetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    public InventorydetailsRepo inventorydetailsrepo;
    @Autowired
    public  CityRepo cityRepo;
    public List<Inventorydetails> inventoryDetailsOfCity (String cityname)
    {
        return inventorydetailsrepo.inventoryDetailsOfCity(cityname);
    }
    public List<Inventorydetails> inventorydetailsFromPincode(String pincode)
    {
        return inventorydetailsrepo.inventoryDetailsFromPincode(pincode);
    }

    public List<Inventorydetails> allInventoryDetails()
    {
        return (List<Inventorydetails>) inventorydetailsrepo.findAll();
    }
    public List<Integer> allInventoryNumber()
    {
        return inventorydetailsrepo.allInventoryNumbers();
    }
    public String pincodeOfInventoryNumber(Integer inventorynumber)
    {
        return  inventorydetailsrepo.pincodeFromInventoryNumber(inventorynumber);
    }

    public Inventorydetails inventoryDetailsByInventoryNumber(Integer inventorynumber )
    {
        Optional<Inventorydetails> inventorydata=inventorydetailsrepo.findById(inventorynumber);
        return  inventorydata.get();
    }

    public int maxValueOfInventoryNumber()
    {
        return (inventorydetailsrepo.maxInventoryNumber());
    }


    public String createInventory(NewInventoryDetails newInventoryDetails)
    {
        City citydata= (cityRepo.findById(newInventoryDetails.getPincode()).orElseThrow(() -> new RuntimeException("City Not found with this Pincode" + newInventoryDetails.getPincode())));
        Inventorydetails inventorydata=new Inventorydetails();
        Integer MaxinventoryNum= maxValueOfInventoryNumber();
        inventorydata.setInventoryNumber(MaxinventoryNum + 1);
        inventorydata.setCity(citydata);
        inventorydata.setNearByLocation(newInventoryDetails.getNearByLocation());
        inventorydata.setPhoneNumber(newInventoryDetails.getPhoneNumber());
        inventorydata.setEmail(newInventoryDetails.getEmail());
//        Date currDate = new Date();
//        Timestamp currtmstmp = new Timestamp(currDate.getTime());
//        inventorydata.setRecordcreatedtime(currtmstmp);
        inventorydetailsrepo.save(inventorydata);
        return "Sucessfully Inventory Created";

    }
    public Inventorydetails updatedInventorydetails(NewInventoryDetails newInventoryDetails, Integer inventorynumber)
    {
        Inventorydetails inventorydata = inventoryDetailsByInventoryNumber(inventorynumber);
        if(inventorydata!=null)
        {
            City citydata= (cityRepo.findById(newInventoryDetails.getPincode())
                    .orElseThrow(() -> new RuntimeException("City Not found with this Pincode" + newInventoryDetails.getPincode())));
            inventorydata.setCity(citydata);
            inventorydata.setEmail(newInventoryDetails.getEmail());
            inventorydata.setNearByLocation(newInventoryDetails.getNearByLocation());
            inventorydata.setPhoneNumber(newInventoryDetails.getPhoneNumber());
            Inventorydetails inventoryupdated =inventorydetailsrepo.save(inventorydata);


//            inventorydata.setInventoryuniquecode(newInventoryDetails.getPincode()+"_"+inventorydata.getInventorynumber());
            return inventoryupdated;
        }
        else {
            return null;
        }
    }
    public String deleteInventoryByInventoryNumber(Integer inventorynumber)
    {
        inventorydetailsrepo.deleteById(inventorynumber);
        return "Successsfully Deleted";
    }

    public List<Object[]> contactDetailsOfInventories()
    {

        return  inventorydetailsrepo.contactDetailsOfInventories();
    }

    public Integer countOfInventoryInPincode(String pincode)
    {
        return inventorydetailsrepo.countOfInventoryInPincode(pincode);
    }

    public Integer candeleteornotcity(String pincode)
    {
        return inventorydetailsrepo.countOfInventoryInPincode(pincode);
    }




}
