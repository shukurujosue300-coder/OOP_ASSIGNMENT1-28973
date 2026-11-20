package com.auca.penalty;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxobligation.TaxObligation;
import com.auca.exceptions.ValidationException;

        public class Penalty implements Identifiable, Validatable {
            private String penaltyId;
    private TaxObligation obligation;
    private String penaltyType;
    private BigDecimal baseAmount;
    private BigDecimal penaltyRate;
    private BigDecimal penaltyAmount;
        public Penalty(){}
            public String getPenaltyId(){return penaltyId;}
    public void setPenaltyId(String v){penaltyId=v;}
    public TaxObligation getObligation(){return obligation;}
    public void setObligation(TaxObligation v){obligation=v;}
    public String getPenaltyType(){return penaltyType;}
    public void setPenaltyType(String v){penaltyType=v;}
    public BigDecimal getBaseAmount(){return baseAmount;}
    public void setBaseAmount(BigDecimal v){baseAmount=v;}
    public BigDecimal getPenaltyRate(){return penaltyRate;}
    public void setPenaltyRate(BigDecimal v){penaltyRate=v;}
    public BigDecimal getPenaltyAmount(){return penaltyAmount;}
    public void setPenaltyAmount(BigDecimal v){penaltyAmount=v;}
    public String getId(){return penaltyId;}
    public String getDisplayName(){return penaltyId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "Penalty{"+"penaltyId="+penaltyId+", "+"obligation="+obligation+", "+"penaltyType="+penaltyType+", "+"baseAmount="+baseAmount+", "+"penaltyRate="+penaltyRate+", "+"penaltyAmount="+penaltyAmount+"}";}
        public void validate() throws ValidationException {if(penaltyId==null||penaltyId.trim().isEmpty()) throw new ValidationException("ID is required."); if(baseAmount==null||baseAmount.signum()<0) throw new ValidationException("baseAmount cannot be negative."); if(penaltyRate==null||penaltyRate.signum()<0) throw new ValidationException("penaltyRate cannot be negative."); if(penaltyAmount==null||penaltyAmount.signum()<0) throw new ValidationException("penaltyAmount cannot be negative.");}
        }
