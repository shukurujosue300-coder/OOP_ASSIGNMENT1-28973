package com.auca.abstracts;
import java.time.LocalDate;
import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;
public abstract class TaxParty implements Identifiable, Validatable {
    private String partyId, firstName, lastName, email, phoneNumber;
    private LocalDate dateOfBirth;
    protected TaxParty() {}
    protected TaxParty(String partyId,String firstName,String lastName,String email,String phoneNumber,LocalDate dateOfBirth){
        this.partyId=partyId; this.firstName=firstName; this.lastName=lastName; this.email=email; this.phoneNumber=phoneNumber; this.dateOfBirth=dateOfBirth;
    }
    public String getPartyId(){return partyId;} public void setPartyId(String v){partyId=v;}
    public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
    public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhoneNumber(){return phoneNumber;} public void setPhoneNumber(String v){phoneNumber=v;}
    public LocalDate getDateOfBirth(){return dateOfBirth;} public void setDateOfBirth(LocalDate v){dateOfBirth=v;}
    public String getId(){return partyId;} public String getDisplayName(){return firstName+" "+lastName;} public boolean isActive(){return true;}
    public void validate() throws ValidationException {
        if(blank(partyId)||blank(firstName)||blank(lastName)) throw new ValidationException("Party ID, first name and last name are required.");
        if(firstName.trim().length()<2||lastName.trim().length()<2) throw new ValidationException("Names must contain at least 2 characters.");
        if(email==null||!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")||email.length()>120) throw new ValidationException("Enter a valid email (maximum 120 characters).");
        if(phoneNumber==null||!phoneNumber.matches("\\+?[0-9]{10,15}")) throw new ValidationException("Phone must contain 10-15 digits, optionally starting with +.");
        if(dateOfBirth==null||!dateOfBirth.isBefore(LocalDate.now())) throw new ValidationException("Date of birth must be a past date.");
    }
    protected boolean blank(String s){return s==null||s.trim().isEmpty();}
    public void printDetails(){System.out.println(getSummary());}
    public String getSummary(){return getDisplayName()+" ["+partyId+"]";}
}
