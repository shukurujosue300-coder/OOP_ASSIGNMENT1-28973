package com.auca.taxobligation;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxobligation.TaxObligation;
import com.auca.taxtype.TaxType;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.exceptions.ValidationException;

        public class TaxObligation implements Identifiable, Validatable {
            private String obligationId;
    private RegisteredTaxpayer taxpayer;
    private TaxType taxType;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private LocalDate dueDate;
        public TaxObligation(){}
            public String getObligationId(){return obligationId;}
    public void setObligationId(String v){obligationId=v;}
    public RegisteredTaxpayer getTaxpayer(){return taxpayer;}
    public void setTaxpayer(RegisteredTaxpayer v){taxpayer=v;}
    public TaxType getTaxType(){return taxType;}
    public void setTaxType(TaxType v){taxType=v;}
    public LocalDate getPeriodStart(){return periodStart;}
    public void setPeriodStart(LocalDate v){periodStart=v;}
    public LocalDate getPeriodEnd(){return periodEnd;}
    public void setPeriodEnd(LocalDate v){periodEnd=v;}
    public LocalDate getDueDate(){return dueDate;}
    public void setDueDate(LocalDate v){dueDate=v;}
    public String getId(){return obligationId;}
    public String getDisplayName(){return obligationId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxObligation{"+"obligationId="+obligationId+", "+"taxpayer="+taxpayer+", "+"taxType="+taxType+", "+"periodStart="+periodStart+", "+"periodEnd="+periodEnd+", "+"dueDate="+dueDate+"}";}
        public void validate() throws ValidationException {if(obligationId==null||obligationId.trim().isEmpty()) throw new ValidationException("ID is required.");}
        }
