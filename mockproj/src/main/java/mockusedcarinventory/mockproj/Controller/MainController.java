package mockusedcarinventory.mockproj.Controller;

import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Entity.cardetails;
import mockusedcarinventory.mockproj.Entity.city;
import mockusedcarinventory.mockproj.Entity.inventorydetails;
import mockusedcarinventory.mockproj.Entity.soldcardetails;
import mockusedcarinventory.mockproj.Service.CarService;
import mockusedcarinventory.mockproj.Service.CityService;
import mockusedcarinventory.mockproj.Component.NewInventoryDetails;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Component.NewCarDetails;
import mockusedcarinventory.mockproj.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class MainController {
    @Autowired
    public CityService cityService;
    @Autowired
    public InventoryService inventoryService;
    @Autowired
    public CarService carService;
    @GetMapping ("/citywithcountofinventory")
    @Transactional(readOnly = false)
    public List<Object[]> getCityInventoryCount()
    {
        return  cityService.getCityInventoryCount();
    }
    @GetMapping("/allinventorydetailsofcity{cityname}")
    @Transactional(readOnly = false)
    public List<inventorydetails> getallinventorydatabycityname(@PathVariable String cityname)
    {
        return inventoryService.getallinventorydetailsbycityname(cityname);
    }
    @GetMapping("/allinventorydetailsfrompincode{pincode}")
    @Transactional(readOnly = false)
    public List<inventorydetails> getallinventorydetailsfrompincode(@PathVariable String pincode)
    {
        return inventoryService.getallinventorydetailsbypincode(pincode);
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
        return inventoryService.CreateInventory(data);
    }


    @PostMapping("/addcar")
    @Transactional(readOnly = false)
    public String createcar(@Valid @RequestBody NewCarDetails data,BindingResult bindingResult)
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

        return carService.CreateCar(data);
    }

    @GetMapping("/read/inventory{inventorynumber}")
    @Transactional(readOnly = false)
    public inventorydetails getInventoryByUniquecode(@PathVariable Integer inventorynumber)
    {
        return inventoryService.getInventorydetailsByuniquecode(inventorynumber);
    }

    @GetMapping("/read/car{salesno}")
    @Transactional(readOnly = false)
    public cardetails getCarDetailsBysalesno(@PathVariable Integer salesno)
    {
        return carService.getCardetailsBySalesno(salesno);
    }

    @GetMapping("/readall/inventory")
    @Transactional(readOnly = false)
    public List<inventorydetails> getallinventorydata()
    {

        return inventoryService.getAllInventoryInfo();
    }


    @GetMapping("/readall/cars")
    @Transactional(readOnly = false)
    public List<cardetails> getallcarsdata()
    {

        return carService.getAllCarsInfo();
    }


    @PutMapping("/update/inventory{inventorynumber}")
    @Transactional(readOnly = false)
    public inventorydetails updateinventorydetailbyuniquecode( @Valid @RequestBody NewInventoryDetails data,@PathVariable Integer inventorynumber)
    {

        return inventoryService.UpdatedInventorydetails(data, inventorynumber);

//
    }
    @PutMapping("/update/car{saleno}")
    @Transactional(readOnly = false)
    public cardetails updatecardetailsbysalesno(@Valid @RequestBody NewCarDetails data,@PathVariable Integer saleno)
    {

        return carService.UpdateCarDetailsbySalesno(data, saleno);

    }

    @DeleteMapping("/delete/inventory{inventorynumber}")
    @Transactional(readOnly = false)
    public String deleteInventoryById(@PathVariable Integer inventorynumber)
    {
        if((carService.candeleteornotinventory(inventorynumber))==0) {
            return inventoryService.DeleteInventoryByUniqueCode(inventorynumber);
        }
        else
            return "Can not delete";
    }

    @DeleteMapping("/delete/car{saleno}")
    @Transactional(readOnly = false)
    public String deleteCarById(@PathVariable Integer saleno)
    {
        return carService.DeleteCarbySalesno(saleno);
    }

    @GetMapping("/allcardetailswithuniquecode{inventorynumber}")
    @Transactional(readOnly = false)
    public List<cardetails> getallcardetailsbyinventoryuniquecode(@PathVariable Integer inventorynumber)
    {
        return carService.getallcardetailsfromuniquecode(inventorynumber);
    }

    @GetMapping("/allcontactdetails")
    @Transactional(readOnly = false)
    public  List<Object[]> contactdetailsofInventory()
    {
        return  inventoryService.contactetailsofinventory();
    }
    @PostMapping("/soldcardata{saleno}")
    @Transactional(readOnly = false)
    public String createsoldcardetails(@Valid @RequestBody Soldcardata soldcardata,@PathVariable Integer saleno,BindingResult bindingResult)
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
        return carService.SoldCar(soldcardata,saleno);
    }
    @GetMapping("/allsolddetails")
    @Transactional(readOnly = false)
    public List<soldcardetails> readSoldcardata()
    {
        return  carService.getAllsoldcardetails();
    }

    @GetMapping("/allcountries")
    @Transactional(readOnly = false)
    public List<String> getallcountries()
    {
        return cityService.getallcountry();
    }
    @GetMapping("/allstatesfromcountry{country}")
    @Transactional(readOnly = false)
    public List<String> getallstatesfromcountry(@PathVariable String country)
    {
        return cityService.getallstatesfromcountry(country);
    }

    @GetMapping("/allcitiesfromstate{statename}/{country}")
    @Transactional(readOnly = false)
    public List<String> getallcitynamefromstate(@PathVariable String statename,@PathVariable String country)
    {
        return cityService.getcitynamefromstatename(statename,country);
    }

    @GetMapping("/allpincodefromcity{cityname}/{statename}")
    @Transactional(readOnly = false)
    public List<String> getallpincodesfromcityname(@PathVariable String cityname,@PathVariable String statename)
    {
        return cityService.getpincodefromcityname(cityname,statename);
    }

    @GetMapping("/alldatafrompincode/{pincode}")
    @Transactional(readOnly = false)
    public List<Map<String,Object>> getalldatafrompincode(@PathVariable String pincode)
    {
        return cityService.getcitydetailbypincode(pincode);
    }
    @PostMapping("/addcity")
    @Transactional(readOnly = false)
    public String addcitydetails(@Valid @RequestBody  city citydata,BindingResult bindingResult)
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
//        city citydatareturn=cityService.createcity(citydata);
        return cityService.createcity(citydata);
    }
    @GetMapping("/allcitydata")
    @Transactional(readOnly = false)
    public  List<city> getallcitydata()
    {
        return cityService.getallcitydata();
    }
    @DeleteMapping("/delete/city{pincode}")
    @Transactional(readOnly = false)
    public String deleteCitybypincode(@PathVariable String pincode)
    {
        if((inventoryService.candeleteornotcity(pincode))==0) {
            return cityService.Deletelocationbypincode(pincode);
        }
        else
            return "Can not delete";
    }
     @GetMapping("/allinventorynumber")
    @Transactional(readOnly = true)
    public List<Integer> getallinventorynumbers()
    {
        return inventoryService.getInventorynumber();
    }
       @GetMapping("/pincodefrominventorynumber{inventorynumber}")
    @Transactional(readOnly = false)
    public String getpincodefrominventorynumber(@PathVariable Integer inventorynumber)
    {
        return inventoryService.getpincodefrominventorynum(inventorynumber);
    }

}
