package hcmute.edu.vn.finalproject20110451.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "game")
public class GameEntity extends BaseEntity{
    @Id
    private String id;

    private String maGame;

    private String tenGame;

    private String moTa;

    private float gia;

    private List<String> maLoais = new ArrayList<>();

    private List<String> thietBis = new ArrayList<>();

    private boolean trangThai = true;
}
