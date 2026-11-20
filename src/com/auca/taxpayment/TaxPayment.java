package com.auca.taxpayment;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxassessment.TaxAssessment;
import com.auca.exceptions.ValidationException;

        public class TaxPayment implements Identifiable, Validatable {
            private String paymentId;
    private TaxAssessment assessment;
    private String paymentReference;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod;
        public TaxPayment(){}
            public String getPaymentId(){return paymentId;}
    public void setPaymentId(String v){paymentId=v;}
    public TaxAssessment getAssessment(){return assessment;}
    public void setAssessment(TaxAssessment v){assessment=v;}
    public String getPaymentReference(){return paymentReference;}
    public void setPaymentReference(String v){paymentReference=v;}
    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal v){amount=v;}
    public LocalDate getPaymentDate(){return paymentDate;}
    public void setPaymentDate(LocalDate v){paymentDate=v;}
    public String getPaymentMethod(){return paymentMethod;}
    public void setPaymentMethod(String v){paymentMethod=v;}
    public String getId(){return paymentId;}
    public String getDisplayName(){return paymentId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxPayment{"+"paymentId="+paymentId+", "+"assessment="+assessment+", "+"paymentReference="+paymentReference+", "+"amount="+amount+", "+"paymentDate="+paymentDate+", "+"paymentMethod="+paymentMethod+"}";}
        public void validate() throws ValidationException {if(paymentId==null||paymentId.trim().isEmpty()) throw new ValidationException("ID is required."); if(amount==null||amount.signum()<0) throw new ValidationException("amount cannot be negative.");}
        }
