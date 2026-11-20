package com.auca.taxrefund;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxassessment.TaxAssessment;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.exceptions.ValidationException;

        public class TaxRefund implements Identifiable, Validatable {
            private String refundId;
    private RegisteredTaxpayer taxpayer;
    private TaxAssessment assessment;
    private BigDecimal requestedAmount;
    private LocalDate requestDate;
    private String status;
        public TaxRefund(){}
            public String getRefundId(){return refundId;}
    public void setRefundId(String v){refundId=v;}
    public RegisteredTaxpayer getTaxpayer(){return taxpayer;}
    public void setTaxpayer(RegisteredTaxpayer v){taxpayer=v;}
    public TaxAssessment getAssessment(){return assessment;}
    public void setAssessment(TaxAssessment v){assessment=v;}
    public BigDecimal getRequestedAmount(){return requestedAmount;}
    public void setRequestedAmount(BigDecimal v){requestedAmount=v;}
    public LocalDate getRequestDate(){return requestDate;}
    public void setRequestDate(LocalDate v){requestDate=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public String getId(){return refundId;}
    public String getDisplayName(){return refundId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxRefund{"+"refundId="+refundId+", "+"taxpayer="+taxpayer+", "+"assessment="+assessment+", "+"requestedAmount="+requestedAmount+", "+"requestDate="+requestDate+", "+"status="+status+"}";}
        public void validate() throws ValidationException {if(refundId==null||refundId.trim().isEmpty()) throw new ValidationException("ID is required."); if(requestedAmount==null||requestedAmount.signum()<0) throw new ValidationException("requestedAmount cannot be negative.");}
        }
