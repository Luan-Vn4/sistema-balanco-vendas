package br.mendonca.testemaven.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utilizado para retornar resultados de consultadas paginadas, além de informações
 * sobre a paginação
 */
public class PagedResult<T> implements Iterable<T> {

    // ATRIBUTOS
    private final ArrayList<T> results;

    private final int currentPage;

    private final int totalElements;

    private final int pageSize;


    // MÉTODOS DE ACESSO
    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return pageSize > 0 ? (int) Math.ceil(totalElements / (double) pageSize) : 0;
    }

    /**
     * Cria um resultado de paginação
     * @param results resultados da busca
     * @param currentPage página atual (a partir de 0)
     * @param totalPages total de páginas
     * @param totalElements total de elementos
     * @param pageSize tamanho da página
     */
    public PagedResult(ArrayList<T> results, int currentPage, int totalPages, int totalElements, int pageSize) {
        this.results = results;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
    }


    // MÉTODOS
    public boolean hasNext() {
        return currentPage < getTotalPages()-1;
    }

    public boolean hasPrevious() {
        return currentPage > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }

    public List<T> toList() {
        return List.copyOf(results);
    }

}
