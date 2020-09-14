package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.FileDeserializer;
import ru.stqa.pft.addressbook.generators.FileSerializer;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validPersonsFromXml () throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml +=line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(PersonData.class);
    List<PersonData> persons = (List<PersonData>)xstream.fromXML(xml);
    return persons.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validPersonsFromJson () throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json +=line;
      line = reader.readLine();
    }
    Gson gson = new GsonBuilder().registerTypeAdapter(PersonData.class, new FileDeserializer()).create();
    System.out.println("json = " + json);
    List<PersonData> persons = gson.fromJson(json, new TypeToken<List<PersonData>>() {}.getType());
    return persons.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validPersonsFromXml")
  public void testPersonCreation(PersonData person) {
    app.goTo().homePage();
    Persons before = app.person().all();
    app.person().create(person);
    app.goTo().homePage();
    assertEquals(app.person().count(), before.size()+1);
    Persons after = app.person().all();
    assertThat(after, equalTo(
            before.whithAdded(person.withId(after.stream().mapToInt((p) -> p.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
  }

}
