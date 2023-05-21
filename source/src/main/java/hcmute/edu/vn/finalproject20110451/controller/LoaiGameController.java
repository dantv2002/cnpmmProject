package hcmute.edu.vn.finalproject20110451.controller;

import hcmute.edu.vn.finalproject20110451.dto.LoaiGame.LoaiGameDto;
import hcmute.edu.vn.finalproject20110451.entity.LoaiGameEntity;
import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import hcmute.edu.vn.finalproject20110451.service.LoaiGame.LoaiGameService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/loai-game")
public class LoaiGameController {
    private  final LoaiGameService loaiGameService;

    public LoaiGameController(LoaiGameService loaiGameService) {
        this.loaiGameService = loaiGameService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LoaiGameDto dto, Principal principal) {
        LoaiGameEntity loaiGame = loaiGameService.create(dto, principal);
        return
                new ResponseEntity<>(loaiGame, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateByMaLoai(@Valid @RequestBody LoaiGameDto dto, Principal principal) {
        LoaiGameEntity loaiGame = loaiGameService.updateByMaLoai(dto, principal);
        return
                new ResponseEntity<>(loaiGame, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{maLoai}")
    public ResponseEntity<?> deleteByMaLoai(@PathVariable String maLoai, @Valid Principal principal) {
        boolean loaiGame = loaiGameService.delete(maLoai, principal);
        return
                new ResponseEntity<>("Mã loại game " + maLoai +" delete status: " + loaiGame, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam String maLoai,
                                    @RequestParam String tenLoai,
                                    @RequestParam String moTa,
                                    @RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam String column,
                                    @RequestParam String sort) {
        return new ResponseEntity<>(loaiGameService.filter(maLoai, tenLoai, moTa, page, size, column, sort),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam String maLoai,
                                    @RequestParam String tenLoai,
                                    @RequestParam String moTa,
                                    @Valid Principal principal) {
        return new ResponseEntity<>(loaiGameService.getOne(maLoai, tenLoai, moTa, principal),
                HttpStatus.OK);
    }
}
