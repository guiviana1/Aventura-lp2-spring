package lp2.aventura.spring.model;

import java.util.List;

public class Missao {
    private final String titulo;
    private final String descricao;
    private final Inimigo alvo;
    private final Item recompensa;
    private final int ouroRecompensa;
    private EstadoMissao estado = EstadoMissao.PENDENTE;

    public Missao(String titulo, String descricao, Inimigo alvo, Item recompensa, int ouroRecompensa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.alvo = alvo;
        this.recompensa = recompensa;
        this.ouroRecompensa = ouroRecompensa;
    }

    public List<String> concluir(Personagem personagem) {
        if (estado == EstadoMissao.CONCLUIDA) {
            return List.of();
        }
        estado = EstadoMissao.CONCLUIDA;
        personagem.adicionarOuro(ouroRecompensa);
        personagem.adicionarItem(recompensa);
        return List.of(
            "Missão concluída: " + titulo,
            "Recompensa: " + ouroRecompensa + " moedas e " + recompensa.getNome());
    }

    public boolean isConcluida() {
        return estado == EstadoMissao.CONCLUIDA;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Inimigo getAlvo() {
        return alvo;
    }

    public Item getRecompensa() {
        return recompensa;
    }

    public int getOuroRecompensa() {
        return ouroRecompensa;
    }

    public EstadoMissao getEstado() {
        return estado;
    }
}
