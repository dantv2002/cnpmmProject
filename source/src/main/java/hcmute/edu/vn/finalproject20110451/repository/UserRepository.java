package hcmute.edu.vn.finalproject20110451.repository;

import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndTrangThai(String email, boolean trangthai);
    boolean existsByEmail(String email);
    Page<UserEntity> findAllByEmailContainsOrNameContains(String email, String name, Pageable pageable);
}
