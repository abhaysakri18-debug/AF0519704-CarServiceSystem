package car;

//NO package needed — default package

public class CarServiceRecord {

 int    id;
 String ownerName;
 String carModel;
 String licenseNo;
 String serviceDate;
 String serviceType;
 double cost;
 String mechanic;

 // Constructor for INSERT (no id)
 public CarServiceRecord(String ownerName, String carModel, String licenseNo,
                          String serviceDate, String serviceType,
                          double cost, String mechanic) {
     this.ownerName   = ownerName;
     this.carModel    = carModel;
     this.licenseNo   = licenseNo;
     this.serviceDate = serviceDate;
     this.serviceType = serviceType;
     this.cost        = cost;
     this.mechanic    = mechanic;
 }

 // Constructor for READ (with id)
 public CarServiceRecord(int id, String ownerName, String carModel,
                          String licenseNo, String serviceDate,
                          String serviceType, double cost, String mechanic) {
     this.id          = id;
     this.ownerName   = ownerName;
     this.carModel    = carModel;
     this.licenseNo   = licenseNo;
     this.serviceDate = serviceDate;
     this.serviceType = serviceType;
     this.cost        = cost;
     this.mechanic    = mechanic;
 }

 public String toString() {
     return "\nID: " + id + " | Owner: " + ownerName + " | Car: " + carModel +
            " | License: " + licenseNo + " | Date: " + serviceDate +
            " | Service: " + serviceType + " | Cost: Rs." + cost +
            " | Mechanic: " + mechanic;
 }
}