package samta.example.samta.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "root")
public class Root {
    @Id
    private int id;
    private String answer;
    private String question;
    private int value;
    private Date airdate;
    private Date created_at;
    private Date updated_at;
    private int category_id;
    private int game_id;
    private String invalid_count;
    private Category category;
}
