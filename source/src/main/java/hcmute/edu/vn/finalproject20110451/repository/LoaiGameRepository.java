package hcmute.edu.vn.finalproject20110451.repository;

import hcmute.edu.vn.finalproject20110451.entity.LoaiGameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoaiGameRepository extends MongoRepository<LoaiGameEntity, String> {
    Optional<LoaiGameEntity> findByMaLoaiAndAndTrangThai(String maLoai, boolean trangThai);
    boolean existsByMaLoai(String maLoai);
    Page<LoaiGameEntity> findAllByMaLoaiContainsAndTenLoaiContainsAndMoTaContains(String maLoai, String tenLoai, String moTa, Pageable pageable);
    Optional<LoaiGameEntity> findByMaLoaiContainsAndTenLoaiContainsAndMoTaContainsAndTrangThai(String maLoai, String tenLoai, String moTa, boolean trangThai);
}
