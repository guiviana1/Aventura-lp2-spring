package lp2.aventura.spring.model;

import java.util.List;

// Herói controlado pelo jogador
public class Personagem {
    private static final int VIDA_MAXIMA = 100;
    private static final int DANO_BASE = 10;

    private final String nome;
    private int vida;
    private int ouro;
    private final Inventario<Item> inventario = new Inventario<>();
    private Arma armaEquipada;

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = VIDA_MAXIMA;
        this.ouro = 0;
    }

    public String atacar(Inimigo inimigo) {
        int dano = DANO_BASE;
        if (armaEquipada != null) {
            dano += armaEquipada.getBonusDano();
        }
        inimigo.receberDano(dano);
        return nome + " atacou " + inimigo.getNome() + ". Dano causado: " + dano;
    }

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    public void curar(int pontos) {
        vida = Math.min(VIDA_MAXIMA, vida + pontos);
    }

    public void adicionarOuro(int quantidade) {
        if (quantidade > 0) {
            ouro += quantidade;
        }
    }

    public void adicionarItem(Item item) {
        inventario.adicionar(item);
    }

    public Item itemNaPosicao(int posicao) throws ItemNaoEncontradoException {
        List<Item> itens = inventario.getItens();
        if (posicao < 1 || posicao > itens.size()) {
            throw new ItemNaoEncontradoException("Não existe item na posição " + posicao + ".");
        }
        return itens.get(posicao - 1);
    }

    public String usarItem(int posicao) throws ItemNaoEncontradoException, ItemNaoUsavelException {
        Item item = itemNaPosicao(posicao);
        if (!(item instanceof Usavel usavel)) {
            throw new ItemNaoUsavelException(item.getNome() + " não pode ser usado diretamente.");
        }
        String mensagem = usavel.usar(this);
        inventario.remover(item);
        return mensagem;
    }

    public String equipar(Arma arma) throws ItemNaoEncontradoException {
        if (inventario.procurar(arma) == null) {
            throw new ItemNaoEncontradoException(arma.getNome() + " não está no inventário.");
        }
        armaEquipada = arma;
        return nome + " equipou " + arma.getNome() + ".";
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

    public int getOuro() {
        return ouro;
    }

    public Inventario<Item> getInventario() {
        return inventario;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }
}
