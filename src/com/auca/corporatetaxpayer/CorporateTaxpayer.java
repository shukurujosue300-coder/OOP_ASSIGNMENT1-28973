package com.auca.corporatetaxpayer;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.abstracts.*;

        import com.auca.exceptions.ValidationException;
        public class CorporateTaxpayer extends RegisteredTaxpayer {
            private String companyRegistrationNo;
    private String legalName;
    private LocalDate incorporationDate;
    private String businessSector;
    private String authorizedRepresentative;
    private BigDecimal annualTurnover;
        public CorporateTaxpayer(){super();}
            public String getCompanyRegistrationNo(){return companyRegistrationNo;}
    public void setCompanyRegistrationNo(String v){companyRegistrationNo=v;}
    public String getLegalName(){return legalName;}
    public void setLegalName(String v){legalName=v;}
    public LocalDate getIncorporationDate(){return incorporationDate;}
    public void setIncorporationDate(LocalDate v){incorporationDate=v;}
    public String getBusinessSector(){return businessSector;}
    public void setBusinessSector(String v){businessSector=v;}
    public String getAuthorizedRepresentative(){return authorizedRepresentative;}
    public void setAuthorizedRepresentative(String v){authorizedRepresentative=v;}
    public BigDecimal getAnnualTurnover(){return annualTurnover;}
    public void setAnnualTurnover(BigDecimal v){annualTurnover=v;}
    @Override public String getDisplayName(){return legalName;}
    @Override public String getSummary(){return getDisplayName()+" | TIN: "+getTin();}
        @Override public void validate() throws ValidationException {
        super.validate();

        if(companyRegistrationNo==null||!companyRegistrationNo.matches("[A-Z0-9-]{5,30}")) throw new ValidationException("Company registration number must be 5-30 uppercase letters, digits or hyphens.");
        if(legalName==null||legalName.trim().length()<3||legalName.length()>120) throw new ValidationException("Legal name must be 3-120 characters.");
        if(annualTurnover==null||annualTurnover.signum()<0) throw new ValidationException("Annual turnover cannot be negative.");
 }
        @Override public void printDetails(){System.out.println(getSummary());}

}
