package mockusedcarinventory.mockproj.Controller;

import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Entity.City;
import mockusedcarinventory.mockproj.Service.CityService;
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

public class CityController {
    @Autowired
    public CityService cityService;
    @Autowired
    public InventoryService inventoryService;
    @GetMapping ("/citywithcountofinventory")
    @Transactional(readOnly = false)
    public List<Object[]> getCityInventoryCount()
    {
        return  cityService.cityDataWithInventoryCount();
    }
    @GetMapping("/allcountries")
    @Transactional(readOnly = false)
    public List<String> getallcountries()
    {
        return cityService.allCountry();
    }
    @GetMapping("/allstatesfromcountry/{country}")
    @Transactional(readOnly = false)
    public List<String> getallstatesfromcountry(@PathVariable String country)
    {
        return cityService.allStatesInCountry(country);
    }

    @GetMapping("/allcitiesfromstate/{statename}/{country}")
    @Transactional(readOnly = false)
    public List<String> getallcitynamefromstate(@PathVariable String statename,@PathVariable String country)
    {
        return cityService.cityNameFromStatename(statename,country);
    }

    @GetMapping("/allpincodefromcity/{cityname}/{statename}")
    @Transactional(readOnly = false)
    public List<String> getallpincodesfromcityname(@PathVariable String cityname,@PathVariable String statename)
    {
        return cityService.allPincodeofCity(cityname,statename);
    }

    @GetMapping("/alldatafrompincode/{pincode}")
    @Transactional(readOnly = false)
    public List<Map<String,Object>> getalldatafrompincode(@PathVariable String pincode)
    {
        return cityService.cityDetailsByPincode(pincode);
    }
    @PostMapping("/addcity")
    @Transactional(readOnly = false)
    public String addcitydetails(@Valid @RequestBody City citydata, BindingResult bindingResult)
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
        return cityService.CreateCity(citydata);
    }
    @DeleteMapping("/delete/city/{pincode}")
    @Transactional(readOnly = false)
    public String deleteCitybypincode(@PathVariable String pincode)
    {
        if((inventoryService.candeleteornotcity(pincode))==0) {
            return cityService.deleteLocationByPincode(pincode);
        }
        else
            return "Can not delete";
    }
}
