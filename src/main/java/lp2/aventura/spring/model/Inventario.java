package lp2.aventura.spring.model;

import java.util.ArrayList;
import java.util.List;

public class Inventario<T> {
    private final List<T> itens = new ArrayList<>();

    public void adicionar(T elemento) {
        itens.add(elemento);
    }

    public boolean remover(T elemento) {
        return itens.remove(elemento);
    }

    public T procurar(T elemento) {
        int posicao = itens.indexOf(elemento);
        if (posicao == -1) {
            return null;
        }
        return itens.get(posicao);
    }

    public List<T> getItens() {
        return new ArrayList<>(itens);
    }

    public int getTamanho() {
        return itens.size();
    }

    public boolean isVazio() {
        return itens.isEmpty();
    }
}
