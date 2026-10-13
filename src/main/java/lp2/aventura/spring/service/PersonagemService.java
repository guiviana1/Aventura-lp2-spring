package lp2.aventura.spring.service;

import lp2.aventura.spring.model.Arma;
import lp2.aventura.spring.model.Item;
import lp2.aventura.spring.model.ItemNaoEncontradoException;
import lp2.aventura.spring.model.ItemNaoUsavelException;
import lp2.aventura.spring.model.Personagem;
import lp2.aventura.spring.model.Pocao;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {

    public Personagem criarHeroi(String nome) {
        Personagem heroi = new Personagem(nome);
        Arma espada = new Arma("Espada Curta", 10, 2);
        heroi.adicionarItem(espada);
        heroi.adicionarItem(new Pocao("Poção de Cura", 20, 30));
        try {
            heroi.equipar(espada);
        } catch (ItemNaoEncontradoException e) {
            throw new IllegalStateException(e);
        }
        return heroi;
    }

    public String usarItem(Personagem heroi, int posicao)
            throws ItemNaoEncontradoException, ItemNaoUsavelException {
        return heroi.usarItem(posicao);
    }

    public String equipar(Personagem heroi, int posicao)
            throws ItemNaoEncontradoException, ItemNaoUsavelException {
        Item item = heroi.itemNaPosicao(posicao);
        if (!(item instanceof Arma arma)) {
            throw new ItemNaoUsavelException(item.getNome() + " não é uma arma.");
        }
        return heroi.equipar(arma);
    }
}
