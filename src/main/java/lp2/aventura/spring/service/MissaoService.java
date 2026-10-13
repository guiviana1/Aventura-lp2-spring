package lp2.aventura.spring.service;

import java.util.List;
import lp2.aventura.spring.model.Arma;
import lp2.aventura.spring.model.Inimigo;
import lp2.aventura.spring.model.Missao;
import lp2.aventura.spring.model.Personagem;
import lp2.aventura.spring.model.Pocao;
import org.springframework.stereotype.Service;

@Service
public class MissaoService {

    public List<Missao> criarMissoesIniciais(Inimigo goblin, Inimigo esqueleto) {
        return List.of(
            new Missao("Problemas na Floresta", "Derrote o Goblin da Floresta",
                goblin, new Pocao("Poção de Cura", 20, 30), 25),
            new Missao("Ossos Inquietos", "Derrote o Esqueleto da Cripta",
                esqueleto, new Arma("Espada de Ferro", 35, 5), 35));
    }

    public List<String> concluir(Missao missao, Personagem heroi) {
        if (missao == null) {
            return List.of();
        }
        return missao.concluir(heroi);
    }
}
