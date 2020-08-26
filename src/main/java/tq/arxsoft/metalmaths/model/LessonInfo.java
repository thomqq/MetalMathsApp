package tq.arxsoft.metalmaths.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonInfo {
    private String name;
    private long id;
    private String path;

    public LessonInfo(String name, long id) {
        this.name = name;
        this.id = id;
        this.path = "";
    }
}
