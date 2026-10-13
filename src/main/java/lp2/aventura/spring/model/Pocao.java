package lp2.aventura.spring.model;

public class Pocao extends Item implements Usavel {
    private final int cura;

    public Pocao(String nome, int valor, int cura) {
        super(nome, valor);
        this.cura = cura;
    }

    public int getCura() {
        return cura;
    }

    @Override
    public String usar(Personagem personagem) {
        personagem.curar(cura);
        return personagem.getNome() + " usou " + getNome() + " e recuperou " + cura + " de vida.";
    }

    @Override
    public String getDescricao() {
        return getNome() + " (poção, cura " + cura + ")";
    }
}
