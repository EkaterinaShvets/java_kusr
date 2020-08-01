package ru.stqa.pft.addressbook;

public class PersonData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String mobilephone;
  private final String workname;
  private final String email_1;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String email;

  public PersonData(String firstname, String middlename, String lastname, String address, String mobilephone, String workname, String email_1, String bday, String bmonth, String byear, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.mobilephone = mobilephone;
    this.workname = workname;
    this.email_1 = email_1;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkname() {
    return workname;
  }

  public String getEmail_1() {
    return email_1;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getEmail() {
    return email;
  }
}
