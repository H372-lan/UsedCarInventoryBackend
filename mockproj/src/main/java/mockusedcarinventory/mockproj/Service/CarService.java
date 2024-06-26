package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Component.NewCarDetails;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Entity.cardetails;
import mockusedcarinventory.mockproj.Entity.soldcardetails;

import java.util.List;

public interface CarService {
    public cardetails getCardetailsBySalesno(Integer saleno );
    public List<cardetails> getAllCarsInfo();
    public int maxValueOfSalesvalue();
    public List<cardetails> getallcardetailsfromuniquecode(Integer inventorynumber);
    public String CreateCar(NewCarDetails newCarDetails);
    public cardetails UpdateCarDetailsbySalesno(NewCarDetails newCarDetails,Integer salesno);
    public String DeleteCarbySalesno(Integer saleno);
    public Integer candeleteornotinventory(Integer inventorynumber);
    public String SoldCar(Soldcardata soldcardata, Integer saleno);
    public List<soldcardetails> getAllsoldcardetails();
}
