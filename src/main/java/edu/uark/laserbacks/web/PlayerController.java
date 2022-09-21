package edu.uark.laserbacks.web;

import edu.uark.laserbacks.player.Player;
import edu.uark.laserbacks.player.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public Optional<Player> getById(@PathVariable String id) {
        return service.getPlayerByID(Integer.valueOf(id));
    }

    @GetMapping
    public List<Player> getPlayers() {
        return service.getAllPlayers();
    }

    @GetMapping(path = "/search")
    public Player getPlayerByCodeName(@RequestParam String codeName) {
        return service.getPlayerByCodeName(codeName);
    }

    @PostMapping
    public ResponseEntity<Player> newPlayer(@RequestBody PlayerForm playerForm) {
        Player player = service.createPlayer(playerForm);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
