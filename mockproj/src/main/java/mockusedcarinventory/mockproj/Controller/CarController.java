package mockusedcarinventory.mockproj.Controller;

import jakarta.validation.Valid;
import mockusedcarinventory.mockproj.Entity.Cardetails;
import mockusedcarinventory.mockproj.Entity.Soldcardetails;
import mockusedcarinventory.mockproj.Service.CarService;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Component.NewCarDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class CarController {
    @Autowired
    public CarService carService;
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
    @GetMapping("/read/car/{salesno}")
    @Transactional(readOnly = false)
    public Cardetails getCarDetailsBysalesno(@PathVariable Integer salesno)
    {
        return carService.carDetailsBySaleNo(salesno);
    }
    @GetMapping("/readall/cars")
    @Transactional(readOnly = false)
    public List<Cardetails> getallcarsdata()
    {

        return carService.allCarData();
    }
    @PutMapping("/update/car/{saleno}")
    @Transactional(readOnly = false)
    public Cardetails updatecardetailsbysalesno(@Valid @RequestBody NewCarDetails data, @PathVariable Integer saleno)
    {

        return carService.UpdateCarDetails(data, saleno);

    }
    @DeleteMapping("/delete/car/{saleno}")
    @Transactional(readOnly = false)
    public String deleteCarById(@PathVariable Integer saleno)
    {
        return carService.DeleteCar(saleno);
    }

    @GetMapping("/allcardetailswithuniquecode/{inventorynumber}")
    @Transactional(readOnly = false)
    public List<Cardetails> getallcardetailsbyinventoryuniquecode(@PathVariable Integer inventorynumber)
    {
        return carService.allCarDataFromInventoryNumber(inventorynumber);
    }
    @PostMapping("/soldcardata/{saleno}")
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
    public List<Soldcardetails> readSoldcardata()
    {
        return  carService.allSoldCarData();
    }



}
