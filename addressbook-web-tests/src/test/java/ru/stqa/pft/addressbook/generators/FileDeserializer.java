package ru.stqa.pft.addressbook.generators;
import com.google.gson.*;
import ru.stqa.pft.addressbook.model.PersonData;

import java.io.File;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class FileDeserializer implements JsonDeserializer<PersonData>
{
  @Override
  public PersonData deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException
  {
    PersonData person = new GsonBuilder()
            .addDeserializationExclusionStrategy(new PhotoExclusionStrategy())
            .create()
            .fromJson(json.getAsJsonObject(), PersonData.class);
    try {
      File file = null;
      if (json.getAsJsonObject().get("photo") != null) {
        String path = String.valueOf(json.getAsJsonObject().get("photo"));
        file = new File(path.substring(1, path.length()-1));
      }
      person.withPhoto(file);
    } catch (IllegalArgumentException ie){}
    return person;
  }

  private final class PhotoExclusionStrategy implements ExclusionStrategy {
  @Override
  public boolean shouldSkipField(FieldAttributes f) {
    return f.getName().equals("photo");
  }

  @Override
  public boolean shouldSkipClass(Class<?> clazz) {
    return false;
  }
}
}