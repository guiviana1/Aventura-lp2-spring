package lp2.aventura.spring.model;

import java.util.ArrayList;
import java.util.List;

// Estado de uma partida em andamento
public class Partida {
    private final long id;
    private final Personagem heroi;
    private final List<Inimigo> inimigos;
    private final List<Missao> missoes;
    private int indiceInimigoAtual = 0;
    private int inimigosDerrotados = 0;
    private boolean encerrada = false;
    private List<String> ultimosEventos = new ArrayList<>();

    public Partida(long id, Personagem heroi, List<Inimigo> inimigos, List<Missao> missoes) {
        this.id = id;
        this.heroi = heroi;
        this.inimigos = new ArrayList<>(inimigos);
        this.missoes = new ArrayList<>(missoes);
    }

    public Inimigo getInimigoAtual() {
        if (indiceInimigoAtual >= inimigos.size()) {
            return null;
        }
        return inimigos.get(indiceInimigoAtual);
    }

    public Missao missaoDe(Inimigo inimigo) {
        for (Missao missao : missoes) {
            if (missao.getAlvo().equals(inimigo)) {
                return missao;
            }
        }
        return null;
    }

    public void avancarInimigo() {
        inimigosDerrotados++;
        indiceInimigoAtual++;
        if (indiceInimigoAtual >= inimigos.size()) {
            encerrada = true;
        }
    }

    public void encerrar() {
        encerrada = true;
    }

    public void registrarEventos(List<String> eventos) {
        ultimosEventos = new ArrayList<>(eventos);
    }

    public long getId() {
        return id;
    }

    public Personagem getHeroi() {
        return heroi;
    }

    public List<Missao> getMissoes() {
        return new ArrayList<>(missoes);
    }

    public int getInimigosDerrotados() {
        return inimigosDerrotados;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    public List<String> getUltimosEventos() {
        return new ArrayList<>(ultimosEventos);
    }
}
