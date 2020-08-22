package tq.arxsoft.metalmaths.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tq.arxsoft.metalmaths.operation.Exercise;

import java.util.List;

@Data
@AllArgsConstructor
public class Lesson {
    private long id;
    private String name;
    List<Exercise> exerciseList;
}
