package com.auca.taxtype;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxtype.TaxType;
import com.auca.exceptions.ValidationException;

        public class TaxType implements Identifiable, Validatable {
            private String taxTypeId;
    private String taxCode;
    private String taxName;
    private BigDecimal rate;
    private String filingFrequency;
    private boolean active;
        public TaxType(){}
            public String getTaxTypeId(){return taxTypeId;}
    public void setTaxTypeId(String v){taxTypeId=v;}
    public String getTaxCode(){return taxCode;}
    public void setTaxCode(String v){taxCode=v;}
    public String getTaxName(){return taxName;}
    public void setTaxName(String v){taxName=v;}
    public BigDecimal getRate(){return rate;}
    public void setRate(BigDecimal v){rate=v;}
    public String getFilingFrequency(){return filingFrequency;}
    public void setFilingFrequency(String v){filingFrequency=v;}
    public boolean getActive(){return active;}
    public void setActive(boolean v){active=v;}
    public String getId(){return taxTypeId;}
    public String getDisplayName(){return taxTypeId;}
    public boolean isActive(){return active;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxType{"+"taxTypeId="+taxTypeId+", "+"taxCode="+taxCode+", "+"taxName="+taxName+", "+"rate="+rate+", "+"filingFrequency="+filingFrequency+", "+"active="+active+"}";}
        public void validate() throws ValidationException {if(taxTypeId==null||taxTypeId.trim().isEmpty()) throw new ValidationException("ID is required."); if(rate==null||rate.signum()<0) throw new ValidationException("rate cannot be negative."); if(rate==null||rate.compareTo(BigDecimal.ZERO)<0||rate.compareTo(new BigDecimal("100"))>0) throw new ValidationException("Rate must be from 0 to 100."); if(!"MONTHLY".equals(filingFrequency)&&!"QUARTERLY".equals(filingFrequency)&&!"ANNUAL".equals(filingFrequency)) throw new ValidationException("Frequency must be MONTHLY, QUARTERLY or ANNUAL.");}
        }
