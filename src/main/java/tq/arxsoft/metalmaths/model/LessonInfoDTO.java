package tq.arxsoft.metalmaths.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class LessonInfoDTO {
    private long id;
    private String name;
}
