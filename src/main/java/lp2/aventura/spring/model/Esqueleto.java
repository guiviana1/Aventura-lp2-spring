package lp2.aventura.spring.model;

import java.util.ArrayList;
import java.util.List;

public class Esqueleto extends Inimigo {
    private int ataquesRealizados = 0;

    public Esqueleto(String nome, int vida, int dano) {
        super(nome, vida, dano);
    }

    @Override
    public List<String> atacar(Personagem personagem) {
        List<String> eventos = new ArrayList<>();
        ataquesRealizados++;
        int danoFinal = getDano();
        if (ataquesRealizados % 3 == 0) {
            danoFinal = getDano() * 2;
            eventos.add(getNome() + " prepara um golpe pesado!");
        }
        personagem.receberDano(danoFinal);
        eventos.add(getNome() + " atacou " + personagem.getNome() + ". Dano recebido: " + danoFinal);
        return eventos;
    }
}
