package lp2.aventura.spring.model;

public class Arma extends Item {
    private final int bonusDano;

    public Arma(String nome, int valor, int bonusDano) {
        super(nome, valor);
        this.bonusDano = bonusDano;
    }

    public int getBonusDano() {
        return bonusDano;
    }

    @Override
    public String getDescricao() {
        return getNome() + " (arma, +" + bonusDano + " de dano)";
    }
}
