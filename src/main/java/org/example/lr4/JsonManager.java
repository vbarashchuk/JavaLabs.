package org.example.lr4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.example.lr4.model.Human;
import org.example.lr4.model.Student;
import org.example.lr4.model.University;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonManager {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeHierarchyAdapter(Human.class, new HumanAdapter())
            .create();

    public String toJson(University university) {
        return gson.toJson(university);
    }

    public University fromJson(String json) {
        University university = gson.fromJson(json, University.class);
        if (university == null) {
            throw new JsonParseException("У JSON немає даних університету.");
        }
        return university;
    }

    public void write(Path file, University university) throws IOException {
        Files.writeString(file, toJson(university));
    }

    public University read(Path file) throws IOException {
        return fromJson(Files.readString(file));
    }

    private static class HumanAdapter
            implements JsonSerializer<Human>, JsonDeserializer<Human> {

        private final Gson plainGson = new Gson();

        @Override
        public JsonElement serialize(Human person, Type type,
                                     JsonSerializationContext context) {
            JsonObject data = plainGson.toJsonTree(person).getAsJsonObject();
            data.addProperty("student", person instanceof Student);
            return data;
        }

        @Override
        public Human deserialize(JsonElement json, Type type,
                                 JsonDeserializationContext context) {
            JsonObject data = json.getAsJsonObject();
            boolean isStudent = type == Student.class
                    || (data.has("student") && data.get("student").getAsBoolean());

            Class<? extends Human> personClass = isStudent ? Student.class : Human.class;
            return plainGson.fromJson(data, personClass);
        }
    }
}