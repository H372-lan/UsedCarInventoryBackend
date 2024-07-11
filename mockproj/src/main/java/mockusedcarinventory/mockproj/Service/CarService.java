package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Component.NewCarDetails;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Entity.Cardetails;
import mockusedcarinventory.mockproj.Entity.Soldcardetails;

import java.util.List;

public interface CarService {
    public Cardetails carDetailsBySaleNo(Integer saleno );
    public List<Cardetails> allCarData();
    public int maxValueOfSaleNo();
    public List<Cardetails> allCarDataFromInventoryNumber(Integer inventorynumber);
    public String CreateCar(NewCarDetails newCarDetails);
    public Cardetails UpdateCarDetails(NewCarDetails newCarDetails, Integer salesno);
    public String DeleteCar(Integer saleno);
    public Integer carCountInaInventory(Integer inventorynumber);
    public String SoldCar(Soldcardata soldcardata, Integer saleno);
    public List<Soldcardetails> allSoldCarData();
}
