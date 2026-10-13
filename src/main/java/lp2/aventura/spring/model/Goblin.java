package lp2.aventura.spring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Goblin extends Inimigo {
    private final Random sorteio = new Random();

    public Goblin(String nome, int vida, int dano) {
        super(nome, vida, dano);
    }

    @Override
    public List<String> atacar(Personagem personagem) {
        List<String> eventos = new ArrayList<>();
        eventos.add(golpear(personagem));
        if (sorteio.nextInt(100) < 30 && personagem.isVivo()) {
            eventos.add(getNome() + " foi rápido e atacou de novo!");
            eventos.add(golpear(personagem));
        }
        return eventos;
    }

    private String golpear(Personagem personagem) {
        personagem.receberDano(getDano());
        return getNome() + " atacou " + personagem.getNome() + ". Dano recebido: " + getDano();
    }
}
