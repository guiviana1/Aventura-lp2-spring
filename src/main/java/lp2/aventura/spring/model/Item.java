package lp2.aventura.spring.model;

public abstract class Item {
    private final String nome;
    private final int valor;

    protected Item(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public abstract String getDescricao();

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
        Item outro = (Item) o;
        return nome.equals(outro.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
