package br.mendonca.testemaven.utils;

/**
 * Utilizado para solicitar consultas paginadas
 */
public class PageRequest {

    private final int page;

    private final int size;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    /**
     * Cria solicitação de paginação
     * @param page página que será exibida (a partir de 0)
     * @param size quantidade de elementos da página que será exibida
     */
    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

}
