package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Schedule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceJson {
    public ServiceJson() {}

    public List<Schedule> read(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Schedule[] obj = mapper.readValue(new File(path), Schedule[].class);
        ArrayList<Schedule> arrayList = new ArrayList<Schedule>(Arrays.asList(obj));
        return arrayList;
    }

    public void write(List<Schedule> arraySchedule, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), arraySchedule);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
