package tq.arxsoft.metalmaths.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExerciseDTO {
    private String formula;
    private List<String> answers;
    private String lang = "EN";
}
