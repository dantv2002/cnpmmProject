package hcmute.edu.vn.finalproject20110451.service.LoaiGame;

import hcmute.edu.vn.finalproject20110451.dto.LoaiGame.LoaiGameDto;
import hcmute.edu.vn.finalproject20110451.entity.LoaiGameEntity;
import hcmute.edu.vn.finalproject20110451.exception.InvalidException;
import hcmute.edu.vn.finalproject20110451.exception.NotFoundException;
import hcmute.edu.vn.finalproject20110451.repository.LoaiGameRepository;
import hcmute.edu.vn.finalproject20110451.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;

@Service
public class LoaiGameServiceImpl implements LoaiGameService{
    private final LoaiGameRepository loaiGameRepository;

    public LoaiGameServiceImpl(LoaiGameRepository loaiGameRepository) {
        this.loaiGameRepository = loaiGameRepository;
    }

    @Override
    public LoaiGameEntity create(LoaiGameDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getMaLoai())) {
            throw new InvalidException("Mã loại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getTenLoai())) {
            throw new InvalidException("Tên loại game không được bỏ trống");
        }
        if (loaiGameRepository.existsByMaLoai(dto.getMaLoai().trim())) {
            throw new InvalidException(String.format("Mã loại %s đã tồn tại",
                    dto.getMaLoai()));
        }

        LoaiGameEntity loaiGame = new LoaiGameEntity();
        loaiGame.setMaLoai(dto.getMaLoai());
        loaiGame.setTenLoai(dto.getTenLoai());
        loaiGame.setMoTa(dto.getMoTa());
        loaiGameRepository.save(loaiGame);
        return loaiGame;
    }

    @Override
    public LoaiGameEntity updateByMaLoai(LoaiGameDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getTenLoai())) {
            throw new InvalidException("Tên loại game không được bỏ trống");
        }
        LoaiGameEntity loaiGame = loaiGameRepository.findByMaLoaiAndAndTrangThai(dto.getMaLoai().trim(), true)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Mã loại %s không tồn tại hoặc đã bị xóa", dto.getMaLoai().trim())));
        loaiGame.setTenLoai(dto.getTenLoai());
        loaiGame.setMoTa(dto.getMoTa());
        loaiGameRepository.save(loaiGame);
        return loaiGame;
    }

    @Override
    public boolean delete(String maLoai, Principal principal) {
        LoaiGameEntity loaiGame = loaiGameRepository.findByMaLoaiAndAndTrangThai(maLoai.trim(), true)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Mã loại %s không tồn tại hoặc đã bị xóa", maLoai.trim())));
        loaiGame.setTrangThai(false);
        loaiGameRepository.save(loaiGame);
        return true;
    }

    @Override
    public Page<LoaiGameEntity> filter(String maLoai, String tenLoai, String moTa, int page, int size, String column, String sort) {
        Pageable pageable = PageUtils.createPageable(page, size, column, sort);
        Page<LoaiGameEntity> pageLoaiGame;
        try{
            pageLoaiGame = loaiGameRepository.findAllByMaLoaiContainsAndTenLoaiContainsAndMoTaContains(maLoai, tenLoai, moTa, pageable);
        }catch(Exception e) {
            throw new NotFoundException("Không tìm thấy thông tin như yêu cầu!");
        }
        return pageLoaiGame;
    }

    @Override
    public LoaiGameEntity getOne(String maLoai, String tenLoai, String moTa, Principal principal) {
        LoaiGameEntity loaiGame = loaiGameRepository.findByMaLoaiContainsAndTenLoaiContainsAndMoTaContainsAndTrangThai(maLoai, tenLoai, moTa, true)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin như yêu cầu!"));
        return loaiGame;
    }

}
