package com.auca.taxassessment;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.taxassessment.TaxAssessment;
import com.auca.taxdeclaration.TaxDeclaration;
import com.auca.exceptions.ValidationException;

        public class TaxAssessment implements Identifiable, Validatable {
            private String assessmentId;
    private TaxDeclaration declaration;
    private BigDecimal assessedTax;
    private BigDecimal adjustmentAmount;
    private LocalDate assessmentDate;
    private String status;
        public TaxAssessment(){}
            public String getAssessmentId(){return assessmentId;}
    public void setAssessmentId(String v){assessmentId=v;}
    public TaxDeclaration getDeclaration(){return declaration;}
    public void setDeclaration(TaxDeclaration v){declaration=v;}
    public BigDecimal getAssessedTax(){return assessedTax;}
    public void setAssessedTax(BigDecimal v){assessedTax=v;}
    public BigDecimal getAdjustmentAmount(){return adjustmentAmount;}
    public void setAdjustmentAmount(BigDecimal v){adjustmentAmount=v;}
    public LocalDate getAssessmentDate(){return assessmentDate;}
    public void setAssessmentDate(LocalDate v){assessmentDate=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public String getId(){return assessmentId;}
    public String getDisplayName(){return assessmentId;}
    public boolean isActive(){return true;}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" | "+toString();}
    public String toString(){return "TaxAssessment{"+"assessmentId="+assessmentId+", "+"declaration="+declaration+", "+"assessedTax="+assessedTax+", "+"adjustmentAmount="+adjustmentAmount+", "+"assessmentDate="+assessmentDate+", "+"status="+status+"}";}
        public void validate() throws ValidationException {if(assessmentId==null||assessmentId.trim().isEmpty()) throw new ValidationException("ID is required."); if(assessedTax==null||assessedTax.signum()<0) throw new ValidationException("assessedTax cannot be negative."); if(adjustmentAmount==null||adjustmentAmount.signum()<0) throw new ValidationException("adjustmentAmount cannot be negative.");}
        }
