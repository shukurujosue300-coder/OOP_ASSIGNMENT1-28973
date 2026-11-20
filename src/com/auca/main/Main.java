package com.auca.main;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.auca.abstracts.*;
import com.auca.interfaces.*;
import com.auca.individualtaxpayer.*;
import com.auca.corporatetaxpayer.*;
import com.auca.revenueofficer.*;
import com.auca.taxtype.*;
import com.auca.taxobligation.*;
import com.auca.taxdeclaration.*;
import com.auca.taxassessment.*;
import com.auca.taxpayment.*;
import com.auca.penalty.*;
import com.auca.taxrefund.*;
import com.auca.taxclearance.*;
import com.auca.exceptions.*;

public class Main {
 private static final Scanner in=new Scanner(System.in);
 private static final IndividualTaxpayer[] individuals=new IndividualTaxpayer[100];
 private static final CorporateTaxpayer[] companies=new CorporateTaxpayer[100];
 private static final RevenueOfficer[] officers=new RevenueOfficer[50];
 private static final TaxType[] types=new TaxType[50];
 private static final TaxObligation[] obligations=new TaxObligation[200];
 private static final TaxDeclaration[] declarations=new TaxDeclaration[200];
 private static final TaxAssessment[] assessments=new TaxAssessment[200];
 private static final TaxPayment[] payments=new TaxPayment[300];
 private static final Penalty[] penalties=new Penalty[200];
 private static final TaxRefund[] refunds=new TaxRefund[100];
 private static final TaxClearance[] clearances=new TaxClearance[100];
 private static int ni,nc,no,nt,nob,nd,na,np,npen,nr,ncl;
 private static String read(String label){System.out.print(label+": ");return in.nextLine().trim();}
 private static BigDecimal money(String label){while(true){try{return new BigDecimal(read(label)).setScale(2,RoundingMode.HALF_UP);}catch(NumberFormatException e){System.out.println("Enter a valid amount.");}}}
 private static LocalDate date(String label){while(true){try{return LocalDate.parse(read(label));}catch(DateTimeParseException e){System.out.println("Use YYYY-MM-DD.");}}}
 private static int number(){try{return Integer.parseInt(read("Select option"));}catch(NumberFormatException e){return -1;}}
 private static void pause(){System.out.println("\nPress ENTER to continue...");in.nextLine();}
 private static RegisteredTaxpayer taxpayer(String tin){for(int i=0;i<ni;i++)if(individuals[i].getTin().equals(tin))return individuals[i];for(int i=0;i<nc;i++)if(companies[i].getTin().equals(tin))return companies[i];return null;}
 private static TaxType taxType(String id){for(int i=0;i<nt;i++)if(types[i].getId().equals(id))return types[i];return null;}
 private static TaxObligation obligation(String id){for(int i=0;i<nob;i++)if(obligations[i].getId().equals(id))return obligations[i];return null;}
 private static TaxDeclaration declaration(String id){for(int i=0;i<nd;i++)if(declarations[i].getId().equals(id))return declarations[i];return null;}
 private static TaxAssessment assessment(String id){for(int i=0;i<na;i++)if(assessments[i].getId().equals(id))return assessments[i];return null;}
 private static void registerIndividual(){
  if(ni==individuals.length){System.out.println("Individual capacity reached.");return;}
  IndividualTaxpayer x=new IndividualTaxpayer();
  try{
   x.setPartyId(read("Party ID"));x.setFirstName(read("First name"));x.setLastName(read("Last name"));x.setEmail(read("Email"));x.setPhoneNumber(read("Phone"));x.setDateOfBirth(date("Date of birth"));
   x.setTin(read("9-digit TIN"));if(taxpayer(x.getTin())!=null)throw new DuplicateRecordException("TIN already exists.");
   x.setRegistrationDate(date("Registration date"));x.setTaxpayerStatus("ACTIVE");x.setDistrict(read("District"));x.setSector(read("Sector"));x.setTaxOffice(read("Tax office"));
   x.setNationalId(read("16-digit national ID"));x.setOccupation(read("Occupation"));x.setBusinessName(read("Business name (or N/A)"));x.setBusinessStartDate(date("Business start date"));x.setAnnualTurnover(money("Annual turnover"));x.setResident("YES".equalsIgnoreCase(read("Resident? YES/NO")));
   x.validate();for(int i=0;i<ni;i++)if(individuals[i].getNationalId().equals(x.getNationalId()))throw new DuplicateRecordException("National ID already exists.");
   individuals[ni++]=x;System.out.println("Individual taxpayer saved.");
  }catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void registerCompany(){
  if(nc==companies.length){System.out.println("Corporate capacity reached.");return;}
  CorporateTaxpayer x=new CorporateTaxpayer();
  try{x.setPartyId(read("Party ID"));x.setFirstName(read("Contact first name"));x.setLastName(read("Contact last name"));x.setEmail(read("Email"));x.setPhoneNumber(read("Phone"));x.setDateOfBirth(date("Representative date of birth"));
   x.setTin(read("9-digit TIN"));if(taxpayer(x.getTin())!=null)throw new DuplicateRecordException("TIN already exists.");
   x.setRegistrationDate(date("Registration date"));x.setTaxpayerStatus("ACTIVE");x.setDistrict(read("District"));x.setSector(read("Sector"));x.setTaxOffice(read("Tax office"));
   x.setCompanyRegistrationNo(read("Company registration number"));x.setLegalName(read("Legal name"));x.setIncorporationDate(date("Incorporation date"));x.setBusinessSector(read("Business sector"));x.setAuthorizedRepresentative(read("Authorized representative"));x.setAnnualTurnover(money("Annual turnover"));
   x.validate();for(int i=0;i<nc;i++)if(companies[i].getCompanyRegistrationNo().equals(x.getCompanyRegistrationNo()))throw new DuplicateRecordException("Company number already exists.");
   companies[nc++]=x;System.out.println("Corporate taxpayer saved.");
  }catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void registerOfficer(){
  if(no==officers.length)return;RevenueOfficer x=new RevenueOfficer();
  try{x.setPartyId(read("Party ID"));x.setFirstName(read("First name"));x.setLastName(read("Last name"));x.setEmail(read("Email"));x.setPhoneNumber(read("Phone"));x.setDateOfBirth(date("Date of birth"));
   x.setStaffNumber(read("Staff number"));x.setDepartment(read("Department"));x.setRole(read("Role"));x.setHireDate(date("Hire date"));x.setApprovalLimit(money("Approval limit"));x.setActive(true);x.validate();officers[no++]=x;System.out.println("Officer saved.");
  }catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void registerType(){
  if(nt==types.length)return;TaxType x=new TaxType();
  try{x.setTaxTypeId(read("Tax type ID"));x.setTaxCode(read("Tax code"));x.setTaxName(read("Tax name"));x.setRate(money("Rate (%)"));x.setFilingFrequency(read("Frequency MONTHLY/QUARTERLY/ANNUAL").toUpperCase());x.setActive(true);x.validate();if(taxType(x.getId())!=null)throw new DuplicateRecordException("Duplicate tax type ID.");types[nt++]=x;System.out.println("Tax type saved.");}catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void createObligation(){
  if(nob==obligations.length)return;TaxObligation x=new TaxObligation();
  try{String tin=read("Taxpayer TIN");RegisteredTaxpayer t=taxpayer(tin);if(t==null||!t.isActive())throw new PrerequisiteMissingException("Active taxpayer not found.");
   String tid=read("Tax type ID");TaxType ty=taxType(tid);if(ty==null||!ty.isActive())throw new PrerequisiteMissingException("Active tax type not found.");
   x.setObligationId(read("Obligation ID"));x.setTaxpayer(t);x.setTaxType(ty);x.setPeriodStart(date("Period start"));x.setPeriodEnd(date("Period end"));x.setDueDate(date("Due date"));
   if(x.getPeriodStart().isAfter(x.getPeriodEnd())||x.getDueDate().isBefore(x.getPeriodEnd()))throw new InvalidTaxPeriodException("Period dates or due date are inconsistent.");
   for(int i=0;i<nob;i++){TaxObligation o=obligations[i];if(o.getTaxpayer()==t&&o.getTaxType()==ty&&!x.getPeriodStart().isAfter(o.getPeriodEnd())&&!x.getPeriodEnd().isBefore(o.getPeriodStart()))throw new DuplicateRecordException("Overlapping obligation exists.");}
   x.validate();obligations[nob++]=x;System.out.println("Obligation saved.");
  }catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void fileDeclaration(){
  if(nd==declarations.length)return;TaxDeclaration x=new TaxDeclaration();
  try{TaxObligation o=obligation(read("Obligation ID"));if(o==null)throw new PrerequisiteMissingException("Obligation not found.");
   for(int i=0;i<nd;i++)if(declarations[i].getObligation()==o&&!"REJECTED".equals(declarations[i].getStatus()))throw new DuplicateRecordException("An active declaration already exists.");
   x.setDeclarationId(read("Declaration ID"));x.setObligation(o);x.setFilingDate(date("Filing date"));x.setTaxableAmount(money("Taxable amount"));
   BigDecimal expected=x.getTaxableAmount().multiply(o.getTaxType().getRate()).divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);
   x.setDeclaredTax(money("Declared tax (expected "+expected+")"));if(x.getDeclaredTax().subtract(expected).abs().compareTo(new BigDecimal("0.01"))>0)throw new InvalidTaxCalculationException("Declared tax does not match expected tax.");
   x.setStatus(x.getFilingDate().isAfter(o.getDueDate())?"LATE":"FILED");x.validate();declarations[nd++]=x;System.out.println("Declaration saved: "+x.getStatus());
  }catch(Exception e){System.out.println("Not saved: "+e.getMessage());}
 }
 private static void assess(){
  if(na==assessments.length)return;try{TaxDeclaration d=declaration(read("Declaration ID"));if(d==null)throw new PrerequisiteMissingException("Declaration not found.");if("REJECTED".equals(d.getStatus()))throw new InvalidStatusTransitionException("Rejected declaration cannot be assessed.");
   TaxAssessment a=new TaxAssessment();a.setAssessmentId(read("Assessment ID"));a.setDeclaration(d);a.setAssessedTax(d.getDeclaredTax());a.setAdjustmentAmount(money("Adjustment amount (0 if none)"));if(a.getAssessedTax().add(a.getAdjustmentAmount()).signum()<0)throw new InvalidTaxCalculationException("Assessment total cannot be negative.");a.setAssessmentDate(LocalDate.now());a.setStatus("DRAFT");a.validate();assessments[na++]=a;System.out.println("Draft assessment created.");
  }catch(Exception e){System.out.println("Assessment failed: "+e.getMessage());}
 }
 private static void confirm(){TaxAssessment a=assessment(read("Assessment ID"));if(a==null){System.out.println("Assessment not found.");return;}if(!"DRAFT".equals(a.getStatus())){System.out.println("Only DRAFT assessments can be confirmed.");return;}a.setStatus("CONFIRMED");System.out.println("Assessment confirmed.");}
 private static BigDecimal paid(TaxAssessment a){BigDecimal total=BigDecimal.ZERO;for(int i=0;i<np;i++)if(payments[i].getAssessment()==a)total=total.add(payments[i].getAmount());return total;}
 private static BigDecimal total(TaxAssessment a){return a.getAssessedTax().add(a.getAdjustmentAmount());}
 private static void payment(){if(np==payments.length)return;try{TaxAssessment a=assessment(read("Assessment ID"));if(a==null||!"CONFIRMED".equals(a.getStatus()))throw new PrerequisiteMissingException("Confirmed assessment not found.");
  TaxPayment p=new TaxPayment();p.setPaymentId(read("Payment ID"));p.setAssessment(a);p.setPaymentReference(read("Unique payment reference"));for(int i=0;i<np;i++)if(payments[i].getPaymentReference().equals(p.getPaymentReference()))throw new DuplicateRecordException("Payment reference already used.");
  p.setAmount(money("Payment amount"));if(p.getAmount().signum()<=0)throw new ValidationException("Payment must be greater than zero.");if(p.getAmount().compareTo(total(a).subtract(paid(a)))>0)throw new OverpaymentException("Payment exceeds outstanding balance.");
  p.setPaymentDate(date("Payment date"));p.setPaymentMethod(read("Payment method"));p.validate();payments[np++]=p;System.out.println("Payment recorded.");
 }catch(Exception e){System.out.println("Payment not saved: "+e.getMessage());}}
 private static void penalty(){if(npen==penalties.length)return;try{TaxObligation o=obligation(read("Obligation ID"));if(o==null)throw new PrerequisiteMissingException("Obligation not found.");for(int i=0;i<npen;i++)if(penalties[i].getObligation()==o&&penalties[i].getPenaltyType().equals("LATE_FILING"))throw new DuplicateRecordException("Late filing penalty already exists.");
  Penalty p=new Penalty();p.setPenaltyId(read("Penalty ID"));p.setObligation(o);p.setPenaltyType("LATE_FILING");p.setBaseAmount(money("Penalty base amount"));p.setPenaltyRate(new BigDecimal("5"));p.setPenaltyAmount(p.getBaseAmount().multiply(p.getPenaltyRate()).divide(new BigDecimal("100"),2,RoundingMode.HALF_UP));penalties[npen++]=p;System.out.println("Illustrative 5% penalty: "+p.getPenaltyAmount());
 }catch(Exception e){System.out.println("Penalty failed: "+e.getMessage());}}
 private static void refund(){if(nr==refunds.length)return;try{RegisteredTaxpayer t=taxpayer(read("Taxpayer TIN"));TaxAssessment a=assessment(read("Assessment ID"));if(t==null||a==null||a.getDeclaration().getObligation().getTaxpayer()!=t)throw new PrerequisiteMissingException("Matching taxpayer/assessment not found.");
  BigDecimal over=paid(a).subtract(total(a));if(over.signum()<=0)throw new OverpaymentException("No verified overpayment exists.");for(int i=0;i<nr;i++)if(refunds[i].getAssessment()==a&&"PENDING".equals(refunds[i].getStatus()))throw new DuplicateRecordException("Pending refund already exists.");
  TaxRefund r=new TaxRefund();r.setRefundId(read("Refund ID"));r.setTaxpayer(t);r.setAssessment(a);r.setRequestedAmount(money("Refund amount"));if(r.getRequestedAmount().signum()<=0||r.getRequestedAmount().compareTo(over)>0)throw new OverpaymentException("Refund exceeds verified overpayment.");r.setRequestDate(LocalDate.now());r.setStatus("PENDING");refunds[nr++]=r;System.out.println("Refund request saved as PENDING.");
  if("YES".equalsIgnoreCase(read("Approve now? YES/NO"))){String staff=read("Approving officer staff number");RevenueOfficer officer=null;for(int i=0;i<no;i++)if(officers[i].getStaffNumber().equals(staff))officer=officers[i];if(officer==null||!officer.isActive()||officer.getApprovalLimit().compareTo(r.getRequestedAmount())<0)throw new UnauthorizedApprovalException("Officer inactive, missing or below approval limit.");r.setStatus("APPROVED");System.out.println("Refund approved by "+officer.getDisplayName());}
 }catch(Exception e){System.out.println("Refund workflow: "+e.getMessage());}}
 private static void clearance(){if(ncl==clearances.length)return;try{RegisteredTaxpayer t=taxpayer(read("Taxpayer TIN"));if(t==null||!t.isActive())throw new PrerequisiteMissingException("Active taxpayer not found.");for(int i=0;i<nd;i++)if(declarations[i].getObligation().getTaxpayer()==t&&"FILED".equals(declarations[i].getStatus()))throw new PrerequisiteMissingException("Unresolved declaration exists.");
  for(int i=0;i<na;i++)if(assessments[i].getDeclaration().getObligation().getTaxpayer()==t&&"CONFIRMED".equals(assessments[i].getStatus())&&paid(assessments[i]).compareTo(total(assessments[i]))<0)throw new PrerequisiteMissingException("Outstanding balance prevents clearance.");
  TaxClearance c=new TaxClearance();c.setClearanceId(read("Clearance ID"));c.setTaxpayer(t);c.setRequestDate(LocalDate.now());c.setExpiryDate(date("Expiry date"));if(!c.getExpiryDate().isAfter(c.getRequestDate()))throw new ValidationException("Expiry must be after request date.");String staff=read("Officer staff number");RevenueOfficer officer=null;for(int i=0;i<no;i++)if(officers[i].getStaffNumber().equals(staff)&&officers[i].isActive())officer=officers[i];if(officer==null)throw new UnauthorizedApprovalException("Active officer required.");c.setApprovedBy(officer);c.setStatus("APPROVED");clearances[ncl++]=c;System.out.println("Tax clearance APPROVED.");
 }catch(Exception e){System.out.println("Clearance not issued: "+e.getMessage());}}
 private static void viewAll(){System.out.println("-- INDIVIDUALS --");for(int i=0;i<ni;i++)individuals[i].printDetails();System.out.println("-- COMPANIES --");for(int i=0;i<nc;i++)companies[i].printDetails();System.out.println("-- OFFICERS --");for(int i=0;i<no;i++)officers[i].printDetails();System.out.println("-- TAX TYPES --");for(int i=0;i<nt;i++)types[i].printDetails();System.out.println("-- OBLIGATIONS --");for(int i=0;i<nob;i++)obligations[i].printDetails();System.out.println("-- DECLARATIONS --");for(int i=0;i<nd;i++)declarations[i].printDetails();System.out.println("-- ASSESSMENTS --");for(int i=0;i<na;i++)assessments[i].printDetails();System.out.println("-- PAYMENTS --");for(int i=0;i<np;i++)payments[i].printDetails();}
 private static void history(boolean assess){if(assess){for(int i=0;i<na;i++)assessments[i].printDetails();}else for(int i=0;i<nd;i++)declarations[i].printDetails();}
 private static void search(){String key=read("Enter TIN or record ID");RegisteredTaxpayer t=taxpayer(key);if(t!=null){t.printDetails();return;}for(int i=0;i<nt;i++)if(types[i].getId().equals(key))types[i].printDetails();for(int i=0;i<nob;i++)if(obligations[i].getId().equals(key))obligations[i].printDetails();for(int i=0;i<nd;i++)if(declarations[i].getId().equals(key))declarations[i].printDetails();for(int i=0;i<na;i++)if(assessments[i].getId().equals(key))assessments[i].printDetails();for(int i=0;i<np;i++)if(payments[i].getId().equals(key))payments[i].printDetails();}
 private static void update(){String tin=read("Taxpayer TIN to update");RegisteredTaxpayer t=taxpayer(tin);if(t==null){System.out.println("Taxpayer not found.");return;}System.out.println("1 District  2 Sector  3 Tax office  4 Status");int f=number();String v=read("New value");switch(f){case 1:t.setDistrict(v);break;case 2:t.setSector(v);break;case 3:t.setTaxOffice(v);break;case 4:if(!v.equals("ACTIVE")&&!v.equals("INACTIVE")&&!v.equals("SUSPENDED")){System.out.println("Invalid status.");return;}t.setTaxpayerStatus(v);break;default:System.out.println("Invalid field.");return;}System.out.println("Record updated.");}
 private static void statement(){RegisteredTaxpayer t=taxpayer(read("Taxpayer TIN"));if(t==null){System.out.println("Not found.");return;}t.printDetails();for(int i=0;i<nob;i++)if(obligations[i].getTaxpayer()==t){obligations[i].printDetails();for(int j=0;j<nd;j++)if(declarations[j].getObligation()==obligations[i])declarations[j].printDetails();for(int j=0;j<na;j++)if(assessments[j].getDeclaration().getObligation()==obligations[i]){assessments[j].printDetails();System.out.println("Paid: "+paid(assessments[j])+" | Outstanding: "+total(assessments[j]).subtract(paid(assessments[j])));}}}
 private static void balances(){for(int i=0;i<na;i++)System.out.println(assessments[i].getId()+" balance = "+total(assessments[i]).subtract(paid(assessments[i])));}
 private static void reports(){System.out.println("Taxpayer count: "+(ni+nc));System.out.println("Obligations: "+nob+" | Declarations: "+nd+" | Assessments: "+na+" | Payments: "+np);BigDecimal received=BigDecimal.ZERO;for(int i=0;i<np;i++)received=received.add(payments[i].getAmount());System.out.println("Total payments received: "+received);}
 private static void polymorphism(){TaxParty[] parties=new TaxParty[ni+nc+no];int k=0;for(int i=0;i<ni;i++)parties[k++]=individuals[i];for(int i=0;i<nc;i++)parties[k++]=companies[i];for(int i=0;i<no;i++)parties[k++]=officers[i];for(TaxParty p:parties)System.out.println(p.getDisplayName()+" => "+p.getSummary());Identifiable[] ids=new Identifiable[nt+ni+nc+no];k=0;for(int i=0;i<ni;i++)ids[k++]=individuals[i];for(int i=0;i<nc;i++)ids[k++]=companies[i];for(int i=0;i<no;i++)ids[k++]=officers[i];for(int i=0;i<nt;i++)ids[k++]=types[i];for(Identifiable id:ids)System.out.println(id.getId()+" | "+id.getDisplayName()+" | active="+id.isActive());}
 private static void menu(){System.out.println("\n========== TAX ADMINISTRATION MANAGEMENT ==========");System.out.println("1 Register Individual Taxpayer   12 Record Tax Payment");System.out.println("2 Register Corporate Taxpayer    13 Calculate / Record Penalty");System.out.println("3 Register Revenue Officer       14 Request / Approve Refund");System.out.println("4 Register Tax Type              15 Request / Issue Tax Clearance");System.out.println("5 Create Tax Obligation          16 Search Record by ID / TIN");System.out.println("6 File Tax Declaration           17 Update Eligible Record");System.out.println("7 Assess Declaration             18 View Taxpayer Statement");System.out.println("8 Confirm Assessment             19 View Outstanding Balances");System.out.println("9 View All Records               20 View Tax Reports");System.out.println("10 View Declaration History      21 Demonstrate Polymorphism");System.out.println("11 View Assessment History        0 Exit");}
 public static void main(String[] args){boolean running=true;while(running){menu();switch(number()){case 1:registerIndividual();break;case 2:registerCompany();break;case 3:registerOfficer();break;case 4:registerType();break;case 5:createObligation();break;case 6:fileDeclaration();break;case 7:assess();break;case 8:confirm();break;case 9:viewAll();break;case 10:history(false);break;case 11:history(true);break;case 12:payment();break;case 13:penalty();break;case 14:refund();break;case 15:clearance();break;case 16:search();break;case 17:update();break;case 18:statement();break;case 19:balances();break;case 20:reports();break;case 21:polymorphism();break;case 0:running=false;System.out.println("Goodbye.");break;default:System.out.println("Unknown option. Choose 0-21.");}if(running)pause();}in.close();}
}
