package hcmute.edu.vn.finalproject20110451.service.Game;

import hcmute.edu.vn.finalproject20110451.dto.Game.GameDto;
import hcmute.edu.vn.finalproject20110451.entity.GameEntity;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.Optional;

public interface GameService {
    GameEntity create(GameDto dto, Principal principal);

    GameEntity updateByMaGame(GameDto dto, Principal principal);

    boolean delete(String maGame, Principal principal);

    Page<GameEntity> filter(String maGame, String tenGame, String moTa, float gia, int page, int size, String column, String sort);
}
