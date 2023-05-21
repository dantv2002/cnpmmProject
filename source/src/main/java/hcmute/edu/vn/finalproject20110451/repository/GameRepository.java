package hcmute.edu.vn.finalproject20110451.repository;

import hcmute.edu.vn.finalproject20110451.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends MongoRepository<GameEntity, String> {
    boolean existsByMaGameAndTrangThai(String maGame, boolean trangThai);
    Optional<GameEntity> findByMaGameAndTrangThai(String maGame, boolean trangThai);
    Page<GameEntity> findAllByMaGameContainsAndTenGameContainsAndMoTaContainsAndGiaEqualsAndTrangThai(
            String maGame, String tenGame, String moTa, float gia, boolean trangThai, Pageable pageable);
}
