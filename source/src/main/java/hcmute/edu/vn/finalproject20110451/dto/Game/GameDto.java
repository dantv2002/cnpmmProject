package hcmute.edu.vn.finalproject20110451.dto.Game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private String maGame;

    private String tenGame;

    private String moTa;

    private float gia;

    private List<String> maLoais = new ArrayList<>();

    private List<String> thietBis = new ArrayList<>();
}
