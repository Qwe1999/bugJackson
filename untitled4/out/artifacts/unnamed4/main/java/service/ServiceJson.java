package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Lesson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceJson {

    public List<Lesson> read(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Lesson[] obj = mapper.readValue(new File(path), Lesson[].class);
        ArrayList<Lesson> arrayList = new ArrayList<Lesson>(Arrays.asList(obj));
        return arrayList;
    }

    public void write(List<Lesson> arrayLesson, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), arrayLesson);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
