package hcmute.edu.vn.finalproject20110451.service.LoaiGame;

import hcmute.edu.vn.finalproject20110451.dto.LoaiGame.LoaiGameDto;
import hcmute.edu.vn.finalproject20110451.entity.LoaiGameEntity;
import org.springframework.data.domain.Page;

import java.security.Principal;

public interface LoaiGameService {
    LoaiGameEntity create(LoaiGameDto dto, Principal principal);

    LoaiGameEntity updateByMaLoai(LoaiGameDto dto, Principal principal);

    boolean delete(String maLoai, Principal principal);

    Page<LoaiGameEntity> filter(String maLoai, String tenLoai, String moTa, int page, int size, String column, String sort);

    LoaiGameEntity getOne(String maLoai, String tenLoai, String moTa, Principal principal);
}
