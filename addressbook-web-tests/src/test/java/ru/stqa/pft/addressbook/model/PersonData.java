package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table (name = "addressbook")
public class PersonData {
  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column (name = "firstname")
  private String firstname;

  @Expose
  @Column (name = "middlename")
  private String middlename;

  @Expose
  @Column (name = "lastname")
  private String lastname;

  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilephone;

  @Expose
  @Column (name = "work")
  @Type(type = "text")
  private String workphone;

  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homephone;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Column (name = "bday", columnDefinition = "tinyint")
  private int bday;

  @Expose
  @Column (name = "bmonth")
  private String bmonth;

  @Expose
  @Column (name = "byear")
  private String byear;

  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column (name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column (name = "email3")
  @Type(type = "text")
  private String email3;

  @Expose
  @Transient
  private String allEmails;

  @Expose
  @Transient
  private String group;

  @Expose
  @Column (name = "phone2")
  @Type(type = "text")
  private String phoneSecondary;

  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

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

  public PersonData withBday(int bday) {
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

  public PersonData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public PersonData withPhoneSecondary(String phoneSecondary) {
    this.phoneSecondary = phoneSecondary;
    return this;
  }

  public PersonData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public PersonData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }


  public PersonData withPhoto (File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public PersonData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public File getPhoto() { return new File (photo); }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getEmail2() {
    return email2;
  }

  public String getPhoneSecondary() {
    return phoneSecondary;
  }

  public String getAllPhones() {
    return allPhones;
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

  public int getBday() {
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
            bday == that.bday &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(middlename, that.middlename) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(mobilephone, that.mobilephone) &&
            Objects.equals(bmonth, that.bmonth) &&
            Objects.equals(byear, that.byear) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, address, mobilephone, bday, bmonth, byear, email);
  }
}
