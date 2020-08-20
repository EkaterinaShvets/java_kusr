package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class PersonData {
  private int id;
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
  private String group;

  public void setId(int id) {
    this.id = id;
  }

  public PersonData(int id, String firstname, String middlename, String lastname, String address, String mobilephone, String workname, String email_1, String bday, String bmonth, String byear, String email, String group) {
    this.id = id;
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
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonData that = (PersonData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  public PersonData(String firstname, String middlename, String lastname, String address, String mobilephone, String workname, String email_1, String bday, String bmonth, String byear, String email, String group) {
    this.id = Integer.MAX_VALUE;
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
    this.group = group;
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

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "PersonData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
