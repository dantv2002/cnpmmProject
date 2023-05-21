package hcmute.edu.vn.finalproject20110451.service.Game;

import hcmute.edu.vn.finalproject20110451.dto.Game.GameDto;
import hcmute.edu.vn.finalproject20110451.entity.GameEntity;
import hcmute.edu.vn.finalproject20110451.entity.LoaiGameEntity;
import hcmute.edu.vn.finalproject20110451.exception.InvalidException;
import hcmute.edu.vn.finalproject20110451.exception.NotFoundException;
import hcmute.edu.vn.finalproject20110451.repository.GameRepository;
import hcmute.edu.vn.finalproject20110451.repository.LoaiGameRepository;
import hcmute.edu.vn.finalproject20110451.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final LoaiGameRepository loaiGameRepository;

    public GameServiceImpl(GameRepository gameRepository, LoaiGameRepository loaiGameRepository) {
        this.gameRepository = gameRepository;
        this.loaiGameRepository = loaiGameRepository;
    }

    @Override
    public GameEntity create(GameDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getMaGame())) {
            throw new InvalidException("Mã game không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getTenGame())) {
            throw new InvalidException("Tên game không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaLoais()) || dto.getMaLoais().isEmpty()) {
            throw new InvalidException("Phải có ít nhất một mã loại game");
        } else {
            List<String> maloais = dto.getMaLoais();
            for (String maloai: maloais ) {
                if(!loaiGameRepository.existsByMaLoai(maloai)){
                    throw new InvalidException("Một trong số mã loại game không hợp lệ");
                }
            }
        }
        if (gameRepository.existsByMaGameAndTrangThai(dto.getMaGame().trim(), true)) {
            throw new InvalidException(String.format("Mã game %s đã tồn tại",
                    dto.getMaGame()));
        }

        GameEntity game = new GameEntity();
        game.setMaGame(dto.getMaGame());
        game.setTenGame(dto.getTenGame());
        game.setMoTa(dto.getMoTa());
        game.setGia(dto.getGia());
        game.setMaLoais(dto.getMaLoais());
        game.setThietBis(dto.getThietBis());
        gameRepository.save(game);
        return game;
    }

    @Override
    public GameEntity updateByMaGame(GameDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getTenGame())) {
            throw new InvalidException("Tên loại game không được bỏ trống");
        }
        GameEntity game = gameRepository.findByMaGameAndTrangThai(dto.getMaGame().trim(), true)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Mã game %s không tồn tại hoặc đã bị xóa", dto.getMaGame().trim())));
        game.setTenGame(dto.getTenGame());
        game.setMoTa(dto.getMoTa());
        game.setGia(dto.getGia());
        game.setMaLoais(dto.getMaLoais());
        game.setThietBis(dto.getThietBis());
        gameRepository.save(game);
        return game;
    }

    @Override
    public boolean delete(String maGame, Principal principal) {
        GameEntity game = gameRepository.findByMaGameAndTrangThai(maGame.trim(), true)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Mã game %s không tồn tại hoặc đã bị xóa", maGame.trim())));
        game.setTrangThai(false);
        gameRepository.save(game);
        return true;
    }

    @Override
    public Page<GameEntity> filter(String maGame, String tenGame, String moTa, float gia, int page, int size, String column, String sort) {
        Pageable pageable = PageUtils.createPageable(page, size, column, sort);
        Page<GameEntity> pageGame;
        try{
            pageGame = gameRepository.findAllByMaGameContainsAndTenGameContainsAndMoTaContainsAndGiaEqualsAndTrangThai(
                    maGame, tenGame, moTa, gia, true, pageable);
        }catch(Exception e) {
            throw new NotFoundException("Không tìm thấy thông tin như yêu cầu!");
        }
        return pageGame;
    }
}
