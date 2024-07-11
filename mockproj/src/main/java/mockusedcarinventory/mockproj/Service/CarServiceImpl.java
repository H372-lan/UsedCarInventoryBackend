package mockusedcarinventory.mockproj.Service;

import mockusedcarinventory.mockproj.Component.NewCarDetails;
import mockusedcarinventory.mockproj.Component.Soldcardata;
import mockusedcarinventory.mockproj.Entity.Cardetails;
import mockusedcarinventory.mockproj.Entity.Soldcardetails;
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
    public Cardetails carDetailsBySaleNo (Integer saleno )
    {
        Optional<Cardetails> cardata=cardetailsRepo.findById(saleno);
        return  cardata.get();
    }
    public List<Cardetails> allCarData ()
    {
        return (List<Cardetails>) cardetailsRepo.findAll();
    }


    public int maxValueOfSaleNo ()
    {

        return cardetailsRepo.maxValueofSaleNo();
    }
    public List<Cardetails> allCarDataFromInventoryNumber(Integer inventorynumber)
    {
        return cardetailsRepo.carDetailsFromInventoryNumber(inventorynumber);
    }
    public List<Soldcardetails> allSoldCarData ()
    {
        return soldCardetailsRepo.findAll();
    }

    public Integer carCountInaInventory(Integer inventorynumber)
    {
        return cardetailsRepo.carCountInaInventory(inventorynumber);
    }


    public String CreateCar(NewCarDetails newCarDetails)
    {
        Cardetails cardata=new Cardetails();
        Integer MaxsalesNum= maxValueOfSaleNo();
        cardata.setSaleNo(MaxsalesNum+1);
        cardata.setInventoryNumber(Integer.parseInt(newCarDetails.getInventoryNumber()));
        cardata.setKmDriven(newCarDetails.getKmDriven());
        cardata.setPincode(newCarDetails.getPincode());
        cardata.setMfd(newCarDetails.getMfd());
        cardata.setTypeOfCar(newCarDetails.getTypeOfCar());
        cardata.setColor(newCarDetails.getColor());
        cardata.setMilage(newCarDetails.getMilage());
        cardata.setModel(newCarDetails.getModel());
        Date currDate= new Date();
        Timestamp currtmstmp= new Timestamp(currDate.getTime());
        cardata.setRecordCreatedTime(currtmstmp);
        cardetailsRepo.save(cardata);
        return "Sucessfully Car Created";

    }
    public Cardetails UpdateCarDetails (NewCarDetails newCarDetails, Integer salesno)
    {
        Cardetails cardata= carDetailsBySaleNo(salesno);
        if(cardata!=null)
        {
            cardata.setInventoryNumber(Integer.parseInt(newCarDetails.getInventoryNumber()));
            cardata.setPincode(newCarDetails.getPincode());
            cardata.setKmDriven(newCarDetails.getKmDriven());
            cardata.setMfd(newCarDetails.getMfd());
            cardata.setTypeOfCar(newCarDetails.getTypeOfCar());
            cardata.setColor(newCarDetails.getColor());
            cardata.setMilage(newCarDetails.getMilage());
            cardata.setModel(newCarDetails.getModel());
            Cardetails updateddata= cardetailsRepo.save(cardata);
            return updateddata;
        }
        else return null;
    }
    public String DeleteCar (Integer saleno)
    {
        cardetailsRepo.deleteById(saleno);
        return "Successfully Deleted";
    }

    public String SoldCar(Soldcardata soldcardata, Integer saleno)
    {
        Cardetails cardata= carDetailsBySaleNo(saleno);
        if(cardata != null) {
            Soldcardetails cardatasold = new Soldcardetails();
            cardatasold.setSaleNo(saleno);
            cardatasold.setInventoryNumber(soldcardata.getInventoryNumber());
            cardatasold.setModel(soldcardata.getModel());
            cardatasold.setTypeOfCar(soldcardata.getTypeOfCar());
            cardatasold.setColor(soldcardata.getColor());
            cardatasold.setAdharNumber(soldcardata.getAdharNumber());
            cardatasold.setOwnerName(soldcardata.getOwnerName());
            cardatasold.setEmail(soldcardata.getEmail());
            cardatasold.setPhoneNumber(soldcardata.getPhoneNumber());
            cardatasold.setSellingDate(soldcardata.getSellingDate());
            soldCardetailsRepo.save(cardatasold);
            cardetailsRepo.deleteById(saleno);
            return "Car Solded Successfully";
        }
        else{
            return  "Enter Valid details";
        }


    }
}
