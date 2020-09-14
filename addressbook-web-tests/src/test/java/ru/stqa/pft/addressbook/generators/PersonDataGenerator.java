package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.PersonData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonDataGenerator {

  @Parameter(names = "-c", description = "Person count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    PersonDataGenerator generator = new PersonDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<PersonData> persons = generatePersons(count);
    if (format.equals("csv")) {
      saveAsCsv(persons, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(persons, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(persons, new File(file));
    } else {
      System.out.println("Unrecognized format "+format);
    }
  }

  private static void saveAsCsv(List<PersonData> persons, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (PersonData person: persons) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                person.getFirstname(), person.getMiddlename(), person.getLastname(), person.getPhoto(),
                person.getAddress(), person.getHomePhone(), person.getMobilePhone(), person.getWorkPhone(),
                person.getEmail(), person.getEmail2(), person.getEmail3(),
                person.getBday(), person.getBmonth(), person.getByear(), person.getGroup()));
      }
    }
   }

  private void saveAsXml(List<PersonData> persons, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(PersonData.class);
    String xml = xstream.toXML(persons);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsJson(List<PersonData> persons, File file) throws IOException {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(File.class, new FileSerializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    String json = gson.toJson(persons);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private static List<PersonData> generatePersons(int count) {
    List<PersonData> persons = new ArrayList<PersonData>();
    File photo = new File("src/test/resources/photo.png");
    for (int i = 0; i < count; i++) {
      persons.add(new PersonData().withFirstname(String.format("firstName_%s", i))
              .withMiddlename(String.format("middleName_%s", i))
              .withLastname(String.format("lastName_%s", i))
              .withPhoto(photo)
              .withAddress(String.format("address_%s", i))
              .withHomePhone(String.format("homePhone_%s", i))
              .withMobilePhone(String.format("mobilePhone_%s", i))
              .withWorkPhone(String.format("workPhone_%s", i))
              .withEmail(String.format("email_%s", i))
              .withEmail2(String.format("email2_%s", i))
              .withEmail3(String.format("email3_%s", i))
              .withBday("7").withBmonth("July").withByear("1970")
              .withGroup("test1"));
    }
    return persons;
  }
}
