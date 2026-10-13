package lp2.aventura.spring.model;

import java.util.List;

public abstract class Inimigo {
    private final String nome;
    private int vida;
    private final int dano;

    protected Inimigo(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }

    public abstract List<String> atacar(Personagem personagem);

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    public boolean isVivo() {
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public String getTipo() {
        return getClass().getSimpleName().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inimigo outro = (Inimigo) o;
        return nome.equals(outro.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
