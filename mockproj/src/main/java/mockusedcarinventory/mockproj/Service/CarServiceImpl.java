package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Component.NewCarDetails;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Entity.cardetails;
import mockusedcarinventory.mockproj.Entity.soldcardetails;
import mockusedcarinventory.mockproj.Repository.CardetailsRepo;
import mockusedcarinventory.mockproj.Repository.SoldCardetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    public CardetailsRepo cardetailsRepo;

    @Autowired
    public SoldCardetailsRepo soldCardetailsRepo;
    public cardetails getCardetailsBySalesno(Integer saleno )
    {
        Optional<cardetails> cardata=cardetailsRepo.findById(saleno);
        return  cardata.get();
    }
    public List<cardetails> getAllCarsInfo()
    {
        return (List<cardetails>) cardetailsRepo.findAll();
    }


    public int maxValueOfSalesvalue()
    {

        return cardetailsRepo.getValueofmaxsaleno();
    }
    public List<cardetails> getallcardetailsfromuniquecode(Integer inventorynumber)
    {
        return cardetailsRepo.getallcardetailswithuniquecode(inventorynumber);
    }
    public List<soldcardetails> getAllsoldcardetails()
    {
        return soldCardetailsRepo.getallcardetailswhicharesold();
    }

    public Integer candeleteornotinventory(Integer inventorynumber)
    {
        return cardetailsRepo.getallcarcountbeforedeletefrominventorynumber(inventorynumber);
    }


    public String CreateCar(NewCarDetails newCarDetails)
    {
        cardetails cardata=new cardetails();
        Integer MaxsalesNum= maxValueOfSalesvalue();
        cardata.setSaleno(MaxsalesNum+1);
        cardata.setInventorynumber(Integer.parseInt(newCarDetails.getInventorynumber()));
        cardata.setKmdriven(newCarDetails.getKmdriven());
        cardata.setPincode(newCarDetails.getPincode());
        cardata.setMfd(newCarDetails.getMfd());
        cardata.setTypeofcar(newCarDetails.getTypeofcar());
        cardata.setColor(newCarDetails.getColor());
        cardata.setMilage(newCarDetails.getMilage());
        cardata.setModel(newCarDetails.getModel());
        Date currDate= new Date();
        Timestamp currtmstmp= new Timestamp(currDate.getTime());
        cardata.setRecordcreatedtime(currtmstmp);
        cardetailsRepo.save(cardata);
        return "Sucessfully Car Created";

    }
    public cardetails UpdateCarDetailsbySalesno(NewCarDetails newCarDetails,Integer salesno)
    {
        cardetails cardata=getCardetailsBySalesno(salesno);
        if(cardata!=null)
        {
            cardata.setInventorynumber(Integer.parseInt(newCarDetails.getInventorynumber()));
            cardata.setPincode(newCarDetails.getPincode());
            cardata.setKmdriven(newCarDetails.getKmdriven());
            cardata.setMfd(newCarDetails.getMfd());
            cardata.setTypeofcar(newCarDetails.getTypeofcar());
            cardata.setColor(newCarDetails.getColor());
            cardata.setMilage(newCarDetails.getMilage());
            cardata.setModel(newCarDetails.getModel());
            cardetails updateddata= cardetailsRepo.save(cardata);
            return updateddata;
        }
        else return null;
    }
    public String DeleteCarbySalesno(Integer saleno)
    {
        cardetailsRepo.deleteById(saleno);
        return "Successfully Deleted";
    }

    public String SoldCar(Soldcardata soldcardata, Integer saleno)
    {
        cardetails cardata=getCardetailsBySalesno(saleno);
        if(cardata != null) {
            soldcardetails cardatasold = new soldcardetails();
            cardatasold.setSaleno(saleno);
            cardatasold.setInventorynumber(soldcardata.getInventorynumber());
            cardatasold.setModel(soldcardata.getModel());
            cardatasold.setTypeofcar(soldcardata.getTypeofcar());
            cardatasold.setColor(soldcardata.getColor());
            cardatasold.setAdharnumber(soldcardata.getAdharnumber());
            cardatasold.setOwnername(soldcardata.getOwnername());
            cardatasold.setEmail(soldcardata.getEmail());
            cardatasold.setPhonenumber(soldcardata.getPhonenumber());
            cardatasold.setSellingdate(soldcardata.getSellingdate());
            soldCardetailsRepo.save(cardatasold);
            cardetailsRepo.deleteById(saleno);
            return "Car Solded Successfully";
        }
        else{
            return  "Enter Valid details";
        }


    }
}
