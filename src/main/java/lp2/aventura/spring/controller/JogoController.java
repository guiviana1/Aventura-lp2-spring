package lp2.aventura.spring.controller;

import java.util.List;
import lp2.aventura.spring.model.Item;
import lp2.aventura.spring.model.ItemNaoEncontradoException;
import lp2.aventura.spring.model.ItemNaoUsavelException;
import lp2.aventura.spring.model.Missao;
import lp2.aventura.spring.model.Partida;
import lp2.aventura.spring.service.JogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Rotas REST do jogo
@RestController
@RequestMapping("/jogo")
public class JogoController {
    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping
    public ResponseEntity<Partida> criar(@RequestBody(required = false) NovaPartidaRequest request) {
        String nome = request == null ? null : request.nomeHeroi();
        Partida partida = jogoService.criarPartida(nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @GetMapping
    public List<Partida> listar() {
        return jogoService.listarPartidas();
    }

    @GetMapping("/{id}")
    public Partida status(@PathVariable long id) {
        return jogoService.buscar(id);
    }

    @GetMapping("/{id}/inventario")
    public List<Item> inventario(@PathVariable long id) {
        return jogoService.buscar(id).getHeroi().getInventario().getItens();
    }

    @GetMapping("/{id}/missoes")
    public List<Missao> missoes(@PathVariable long id) {
        return jogoService.buscar(id).getMissoes();
    }

    @PostMapping("/{id}/atacar")
    public Partida atacar(@PathVariable long id) {
        return jogoService.atacar(id);
    }

    @PostMapping("/{id}/usar-item/{posicao}")
    public Partida usarItem(@PathVariable long id, @PathVariable int posicao)
            throws ItemNaoEncontradoException, ItemNaoUsavelException {
        return jogoService.usarItem(id, posicao);
    }

    @PostMapping("/{id}/equipar/{posicao}")
    public Partida equipar(@PathVariable long id, @PathVariable int posicao)
            throws ItemNaoEncontradoException, ItemNaoUsavelException {
        return jogoService.equipar(id, posicao);
    }
}
