package com.auca.revenueofficer;
        import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.abstracts.*;

        import com.auca.exceptions.ValidationException;
        public class RevenueOfficer extends TaxParty {
            private String staffNumber;
    private String department;
    private String role;
    private LocalDate hireDate;
    private BigDecimal approvalLimit;
    private boolean active;
        public RevenueOfficer(){super();}
            public String getStaffNumber(){return staffNumber;}
    public void setStaffNumber(String v){staffNumber=v;}
    public String getDepartment(){return department;}
    public void setDepartment(String v){department=v;}
    public String getRole(){return role;}
    public void setRole(String v){role=v;}
    public LocalDate getHireDate(){return hireDate;}
    public void setHireDate(LocalDate v){hireDate=v;}
    public BigDecimal getApprovalLimit(){return approvalLimit;}
    public void setApprovalLimit(BigDecimal v){approvalLimit=v;}
    public boolean getActive(){return active;}
    public void setActive(boolean v){active=v;}
    @Override public String getDisplayName(){return getFirstName()+" "+getLastName()+" ("+role+")";}
    @Override public String getSummary(){return getDisplayName()+" | Staff: "+staffNumber;}
        @Override public void validate() throws ValidationException {
        super.validate();
        if(staffNumber==null||staffNumber.trim().isEmpty()||department==null||role==null||hireDate==null||approvalLimit==null||approvalLimit.signum()<0) throw new ValidationException("Complete all officer fields; approval limit cannot be negative.");
 }
        @Override public void printDetails(){System.out.println(getSummary());}

@Override public boolean isActive(){return active;}

}
