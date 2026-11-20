package com.auca.taxclearance;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.revenueofficer.RevenueOfficer;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.exceptions.ValidationException;

        public class TaxClearance implements Identifiable, Validatable {
            private String clearanceId;
    private RegisteredTaxpayer taxpayer;
    private LocalDate requestDate;
    private LocalDate expiryDate;
    private String status;
    private RevenueOfficer approvedBy;
        public TaxClearance(){}
            public String getClearanceId(){return clearanceId;}
    public void setClearanceId(String v){clearanceId=v;}
    public RegisteredTaxpayer getTaxpayer(){return taxpayer;}
    public void setTaxpayer(RegisteredTaxpayer v){taxpayer=v;}
    public LocalDate getRequestDate(){return requestDate;}
    public void setRequestDate(LocalDate v){requestDate=v;}
    public LocalDate getExpiryDate(){return expiryDate;}
    public void setExpiryDate(LocalDate v){expiryDate=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public RevenueOfficer getApprovedBy(){return approvedBy;}
    public void setApprovedBy(RevenueOfficer v){approvedBy=v;}
    public String getId(){return clearanceId;}
    public String getDisplayName(){return clearanceId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxClearance{"+"clearanceId="+clearanceId+", "+"taxpayer="+taxpayer+", "+"requestDate="+requestDate+", "+"expiryDate="+expiryDate+", "+"status="+status+", "+"approvedBy="+approvedBy+"}";}
        public void validate() throws ValidationException {if(clearanceId==null||clearanceId.trim().isEmpty()) throw new ValidationException("ID is required.");}
        }
