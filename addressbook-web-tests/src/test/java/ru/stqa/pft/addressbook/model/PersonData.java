package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class PersonData {
  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String middlename;
  private String lastname;
  private String address;
  private String mobilephone;
  private String workphone;
  private String homephone;
  private String bday;
  private String bmonth;
  private String byear;
  private String email;
  private String group;

  public PersonData withId(int id) {
    this.id = id;
    return this;
  }

  public PersonData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public PersonData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public PersonData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public PersonData withAddress(String address) {
    this.address = address;
    return this;
  }

  public PersonData withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public PersonData withWorkPhone(String workname) {
    this.workphone = workname;
    return this;
  }

  public PersonData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public PersonData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public PersonData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public PersonData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public PersonData withEmail(String email) {
    this.email = email;
    return this;
  }

  public PersonData withGroup(String group) {
    this.group = group;
    return this;
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

  public String getMobilePhone() {
    return mobilephone;
  }

  public String getWorkPhone() {
    return workphone;
  }

  public String getHomePhone() {
    return homephone;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonData that = (PersonData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
