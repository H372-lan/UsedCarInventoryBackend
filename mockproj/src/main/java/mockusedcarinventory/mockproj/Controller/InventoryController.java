package mockusedcarinventory.mockproj.Controller;

import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Entity.City;
import mockusedcarinventory.mockproj.Entity.Inventorydetails;
import mockusedcarinventory.mockproj.Service.CarService;
import mockusedcarinventory.mockproj.Service.CityService;
import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class InventoryController {
    @Autowired
    public InventoryService inventoryService;
    @Autowired
    public CityService cityService;
    @Autowired
    public CarService carService;
    @GetMapping("/allinventorydetailsofcity/{cityname}")
    @Transactional(readOnly = false)
    public List<Inventorydetails> getallinventorydatabycityname(@PathVariable String cityname)
    {
        return inventoryService.inventoryDetailsOfCity(cityname);
    }
    @GetMapping("/allinventorydetailsfrompincode/{pincode}")
    @Transactional(readOnly = false)
    public List<Inventorydetails> getallinventorydetailsfrompincode(@PathVariable String pincode)
    {
        return inventoryService.inventorydetailsFromPincode(pincode);
    }




    @PostMapping("/addinventory")
    @Transactional(readOnly = false)
    public String createinventory(@Valid @RequestBody NewInventoryDetails data, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            StringBuilder errors= new StringBuilder();
            List<FieldError> fieldErrors=bindingResult.getFieldErrors();
            for(FieldError error:fieldErrors)
            {
                errors.append(error.getDefaultMessage()+":");
            }
            return errors.toString();
        }
        return inventoryService.createInventory(data);
    }
    @GetMapping("/read/inventory/{inventorynumber}")
    @Transactional(readOnly = false)
    public Inventorydetails getInventoryByUniquecode(@PathVariable Integer inventorynumber)
    {
        return inventoryService.inventoryDetailsByInventoryNumber(inventorynumber);
    }
    @GetMapping("/readall/inventory")
    @Transactional(readOnly = false)
    public List<Inventorydetails> getallinventorydata()
    {

        return inventoryService.allInventoryDetails();
    }
    @PutMapping("/update/inventory/{inventorynumber}")
    @Transactional(readOnly = false)
    public Inventorydetails updateinventorydetailbyuniquecode(@Valid @RequestBody NewInventoryDetails data, @PathVariable Integer inventorynumber)
    {

        return inventoryService.updatedInventorydetails(data, inventorynumber);

//
    }
    @GetMapping("/allinventorynumber")
    @Transactional(readOnly = false)
    public List<Integer> getallinventorynumbers()
    {
        return inventoryService.allInventoryNumber();
    }
    @GetMapping("/pincodefrominventorynumber/{inventorynumber}")
    @Transactional(readOnly = false)
    public String getpincodefrominventorynumber(@PathVariable Integer inventorynumber)
    {
        return inventoryService.pincodeOfInventoryNumber(inventorynumber);
    }
    @GetMapping("/allcitydata")
    @Transactional(readOnly = false)
    public  List<City> getallcitydata()
    {
        return cityService.allCityData();
    }
    @DeleteMapping("/delete/City/{pincode}")
    @Transactional(readOnly = false)
    public String deleteCitybypincode(@PathVariable String pincode)
    {
        if((inventoryService.countOfInventoryInPincode(pincode))==0) {
            return cityService.deleteLocationByPincode(pincode);
        }
        else
            return "Can not delete";
    }
    @DeleteMapping("/delete/inventory/{inventorynumber}")
    @Transactional(readOnly = false)
    public String deleteInventoryById(@PathVariable Integer inventorynumber)
    {
        if((carService.carCountInaInventory(inventorynumber))==0) {
            return inventoryService.deleteInventoryByInventoryNumber(inventorynumber);
        }
        else
            return "Can not delete";
    }
    @GetMapping("/allcontactdetails")
    @Transactional(readOnly = false)
    public  List<Object[]> contactdetailsofInventory()
    {
        return  inventoryService.contactDetailsOfInventories();
    }

}
