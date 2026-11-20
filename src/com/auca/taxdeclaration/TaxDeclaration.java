package com.auca.taxdeclaration;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxdeclaration.TaxDeclaration;
import com.auca.taxobligation.TaxObligation;
import com.auca.exceptions.ValidationException;

        public class TaxDeclaration implements Identifiable, Validatable {
            private String declarationId;
    private TaxObligation obligation;
    private LocalDate filingDate;
    private BigDecimal taxableAmount;
    private BigDecimal declaredTax;
    private String status;
        public TaxDeclaration(){}
            public String getDeclarationId(){return declarationId;}
    public void setDeclarationId(String v){declarationId=v;}
    public TaxObligation getObligation(){return obligation;}
    public void setObligation(TaxObligation v){obligation=v;}
    public LocalDate getFilingDate(){return filingDate;}
    public void setFilingDate(LocalDate v){filingDate=v;}
    public BigDecimal getTaxableAmount(){return taxableAmount;}
    public void setTaxableAmount(BigDecimal v){taxableAmount=v;}
    public BigDecimal getDeclaredTax(){return declaredTax;}
    public void setDeclaredTax(BigDecimal v){declaredTax=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public String getId(){return declarationId;}
    public String getDisplayName(){return declarationId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxDeclaration{"+"declarationId="+declarationId+", "+"obligation="+obligation+", "+"filingDate="+filingDate+", "+"taxableAmount="+taxableAmount+", "+"declaredTax="+declaredTax+", "+"status="+status+"}";}
        public void validate() throws ValidationException {if(declarationId==null||declarationId.trim().isEmpty()) throw new ValidationException("ID is required."); if(taxableAmount==null||taxableAmount.signum()<0) throw new ValidationException("taxableAmount cannot be negative."); if(declaredTax==null||declaredTax.signum()<0) throw new ValidationException("declaredTax cannot be negative.");}
        }
