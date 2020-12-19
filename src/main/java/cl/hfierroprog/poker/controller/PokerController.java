package cl.hfierroprog.poker.controller;

import cl.hfierroprog.poker.service.PokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de poker.
 */
@RestController
public class PokerController {

    /**
     * Injección de dependencia de poderService.
     */
    @Autowired
    private PokerService pokerService;

    /**
     * Metodo que realiza el juego.
     * @param players cantidad de jugadores a jugar.
     * @return Lista de jugadores ordenadas por ganador o error de input.
     */
    @GetMapping("/poker/players/{players}")
    public ResponseEntity<Object> poker(@PathVariable("players") int players) {
        if(players > 0 && players <= 10) {
            return new ResponseEntity<>(pokerService.letsPoker(players), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Debe ingresar un rango entre 1 y 10", HttpStatus.BAD_REQUEST);
        }
    }
}
