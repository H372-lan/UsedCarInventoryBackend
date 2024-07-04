package mockusedcarinventory.mockproj.Service;

import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Entity.city;
import mockusedcarinventory.mockproj.Entity.inventorydetails;
import mockusedcarinventory.mockproj.Repository.CityRepo;
import mockusedcarinventory.mockproj.Repository.InventorydetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    public InventorydetailsRepo inventorydetailsrepo;
    @Autowired
    public  CityRepo cityRepo;
    public List<inventorydetails> getallinventorydetailsbycityname(String cityname)
    {
        return inventorydetailsrepo.getallinventorydetailsofacity(cityname);
    }
    public List<inventorydetails> getallinventorydetailsbypincode(String pincode)
    {
        return inventorydetailsrepo.getallinventorydetailfrompincode(pincode);
    }

    public List<inventorydetails> getAllInventoryInfo()
    {
        return (List<inventorydetails>) inventorydetailsrepo.findAll();
    }
     public List<Integer> getInventorynumber()
    {
        return inventorydetailsrepo.getallinventorynumber();
    }

    public inventorydetails getInventorydetailsByuniquecode(Integer inventorynumber )
    {
        Optional<inventorydetails> inventorydata=inventorydetailsrepo.findById(inventorynumber);
        return  inventorydata.get();
    }

    public int maxValueOfInventoryNumber()
    {
        return (inventorydetailsrepo.getValueofMaxInventoryNo());
    }


    public String CreateInventory(NewInventoryDetails newInventoryDetails)
    {
        city citydata= (cityRepo.findById(newInventoryDetails.getPincode()).orElseThrow(() -> new RuntimeException("City Not found with this Pincode" + newInventoryDetails.getPincode())));
        inventorydetails inventorydata=new inventorydetails();
        Integer MaxinventoryNum= maxValueOfInventoryNumber();
        inventorydata.setInventorynumber(MaxinventoryNum + 1);
        inventorydata.setCity(citydata);
        inventorydata.setNearbylocation(newInventoryDetails.getNearbylocation());
        inventorydata.setPhonenumber(newInventoryDetails.getPhonenumber());
        inventorydata.setEmail(newInventoryDetails.getEmail());
//        Date currDate = new Date();
//        Timestamp currtmstmp = new Timestamp(currDate.getTime());
//        inventorydata.setRecordcreatedtime(currtmstmp);
        inventorydetailsrepo.save(inventorydata);
        return "Sucessfully Inventory Created";

    }
    public inventorydetails UpdatedInventorydetails( NewInventoryDetails newInventoryDetails, Integer inventorynumber)
    {
        inventorydetails inventorydata = getInventorydetailsByuniquecode(inventorynumber);
        if(inventorydata!=null)
        {
            city citydata= (cityRepo.findById(newInventoryDetails.getPincode())
                    .orElseThrow(() -> new RuntimeException("City Not found with this Pincode" + newInventoryDetails.getPincode())));
            inventorydata.setCity(citydata);
            inventorydata.setEmail(newInventoryDetails.getEmail());
            inventorydata.setNearbylocation(newInventoryDetails.getNearbylocation());
            inventorydata.setPhonenumber(newInventoryDetails.getPhonenumber());
            inventorydetails inventoryupdated =inventorydetailsrepo.save(inventorydata);


//            inventorydata.setInventoryuniquecode(newInventoryDetails.getPincode()+"_"+inventorydata.getInventorynumber());
            return inventoryupdated;
        }
        else {
            return null;
        }
    }
    public String DeleteInventoryByUniqueCode(Integer inventorynumber)
    {
        inventorydetailsrepo.deleteById(inventorynumber);
        return "Successsfully Deleted";
    }

    public List<Object[]> contactetailsofinventory()
    {

        return  inventorydetailsrepo.getcontactdetailsfrominventory();
    }

    public Integer candeleteornotcity(String pincode)
    {
        return inventorydetailsrepo.getallinventorycountbeforedeletecity(pincode);
    }





}
