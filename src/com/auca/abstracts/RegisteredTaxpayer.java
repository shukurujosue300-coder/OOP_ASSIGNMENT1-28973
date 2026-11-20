package com.auca.abstracts;
import java.time.LocalDate;
import com.auca.exceptions.ValidationException;
public abstract class RegisteredTaxpayer extends TaxParty {
    private String tin, taxpayerStatus, district, sector, taxOffice;
    private LocalDate registrationDate;
    protected RegisteredTaxpayer(){}
    protected RegisteredTaxpayer(String partyId,String firstName,String lastName,String email,String phone,LocalDate dob,
      String tin,LocalDate registrationDate,String taxpayerStatus,String district,String sector,String taxOffice){
      super(partyId,firstName,lastName,email,phone,dob); this.tin=tin;this.registrationDate=registrationDate;
      this.taxpayerStatus=taxpayerStatus;this.district=district;this.sector=sector;this.taxOffice=taxOffice;
    }
    public String getTin(){return tin;} public void setTin(String v){tin=v;}
    public LocalDate getRegistrationDate(){return registrationDate;} public void setRegistrationDate(LocalDate v){registrationDate=v;}
    public String getTaxpayerStatus(){return taxpayerStatus;} public void setTaxpayerStatus(String v){taxpayerStatus=v;}
    public String getDistrict(){return district;} public void setDistrict(String v){district=v;}
    public String getSector(){return sector;} public void setSector(String v){sector=v;}
    public String getTaxOffice(){return taxOffice;} public void setTaxOffice(String v){taxOffice=v;}
    public boolean isActive(){return "ACTIVE".equalsIgnoreCase(taxpayerStatus);}
    @Override public void validate() throws ValidationException {
      super.validate();
      if(tin==null||!tin.matches("[0-9]{9}")) throw new ValidationException("TIN must contain exactly 9 digits.");
      if(registrationDate==null||registrationDate.isAfter(LocalDate.now())) throw new ValidationException("Registration date is invalid.");
      if(taxpayerStatus==null||!(taxpayerStatus.equals("ACTIVE")||taxpayerStatus.equals("INACTIVE")||taxpayerStatus.equals("SUSPENDED"))) throw new ValidationException("Status must be ACTIVE, INACTIVE or SUSPENDED.");
      if(blank(district)||blank(sector)||blank(taxOffice)) throw new ValidationException("District, sector and tax office are required.");
    }
    @Override public String getSummary(){return super.getSummary()+" | TIN: "+tin+" | "+taxpayerStatus;}
}
