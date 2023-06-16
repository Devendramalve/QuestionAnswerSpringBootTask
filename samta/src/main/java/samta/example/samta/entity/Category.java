package samta.example.samta.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "category")
public class Category {
    @Id
    private int id;
    private String title;
    private Date created_at;
    private Date updated_at;
    private int clues_count;

}
