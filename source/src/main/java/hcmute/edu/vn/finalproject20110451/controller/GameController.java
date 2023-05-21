package hcmute.edu.vn.finalproject20110451.controller;

import hcmute.edu.vn.finalproject20110451.dto.Game.GameDto;
import hcmute.edu.vn.finalproject20110451.entity.GameEntity;
import hcmute.edu.vn.finalproject20110451.service.Game.GameService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> create(@Valid @RequestBody GameDto dto, Principal principal) {
        GameEntity game = gameService.create(dto, principal);
        return
                new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping
    public ResponseEntity<?> updateByMaGame(@Valid @RequestBody GameDto dto, Principal principal) {
        GameEntity game = gameService.updateByMaGame(dto, principal);
        return
                new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{maGame}")
    public ResponseEntity<?> deleteByMaGame(@PathVariable String maGame, @Valid Principal principal) {
        boolean game = gameService.delete(maGame, principal);
        return
                new ResponseEntity<>("Mã game " + maGame +" delete status: " + game, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{maGame}")
    public ResponseEntity<?> deleteByMaGameWithAdmindRole(@PathVariable String maGame, @Valid Principal principal) {
        boolean game = gameService.deleteByAdmin(maGame, principal);
        return
                new ResponseEntity<>("Mã game " + maGame +" delete status: " + game + "by admin: "+principal.getName(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam String maGame,
                                    @RequestParam String tenGame,
                                    @RequestParam String moTa,
                                    @RequestParam float gia,
                                    @RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam String column,
                                    @RequestParam String sort) {
        return new ResponseEntity<Page<GameEntity>>(gameService.filter(maGame, tenGame, moTa, gia, page, size, column, sort),
                HttpStatus.OK);
    }
}
