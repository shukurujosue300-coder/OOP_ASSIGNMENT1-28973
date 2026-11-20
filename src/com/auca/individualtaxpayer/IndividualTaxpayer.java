package com.auca.individualtaxpayer;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.abstracts.*;

        import com.auca.exceptions.ValidationException;
        public class IndividualTaxpayer extends RegisteredTaxpayer {
            private String nationalId;
    private String occupation;
    private String businessName;
    private LocalDate businessStartDate;
    private BigDecimal annualTurnover;
    private boolean resident;
        public IndividualTaxpayer(){super();}
            public String getNationalId(){return nationalId;}
    public void setNationalId(String v){nationalId=v;}
    public String getOccupation(){return occupation;}
    public void setOccupation(String v){occupation=v;}
    public String getBusinessName(){return businessName;}
    public void setBusinessName(String v){businessName=v;}
    public LocalDate getBusinessStartDate(){return businessStartDate;}
    public void setBusinessStartDate(LocalDate v){businessStartDate=v;}
    public BigDecimal getAnnualTurnover(){return annualTurnover;}
    public void setAnnualTurnover(BigDecimal v){annualTurnover=v;}
    public boolean getResident(){return resident;}
    public void setResident(boolean v){resident=v;}
    @Override public String getDisplayName(){return getFirstName()+" "+getLastName();}
    @Override public String getSummary(){return getDisplayName()+" | TIN: "+getTin();}
        @Override public void validate() throws ValidationException {
        super.validate();

        if(nationalId==null||!nationalId.matches("[0-9]{16}")) throw new ValidationException("National ID must contain exactly 16 digits.");
        if(annualTurnover==null||annualTurnover.signum()<0) throw new ValidationException("Annual turnover cannot be negative.");
        if(businessStartDate==null||!businessStartDate.isBefore(getRegistrationDate())) throw new ValidationException("Business start date must precede registration.");
 }
        @Override public void printDetails(){System.out.println(getSummary());}

}
