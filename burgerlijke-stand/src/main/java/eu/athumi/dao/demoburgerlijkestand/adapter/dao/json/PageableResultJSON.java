package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

import java.util.List;

public class PageableResultJSON<T> {


    private List<T> elementen;

    private long totaalAantalElementen;
    private long totaalAantalPaginas;
    private long huidigePagina;
    private long aantalElementenOpPagina;

    public PageableResultJSON() {}

    public PageableResultJSON(
            List<T> elementen,
            long totaalAantalElementen,
            long huidigePagina,
            long aantalElementenOpPagina,
            long aantalPaginas
    ) {
        this.elementen = elementen;
        this.totaalAantalElementen = totaalAantalElementen;
        this.huidigePagina = huidigePagina;
        this.aantalElementenOpPagina = aantalElementenOpPagina;
        this.totaalAantalPaginas = aantalPaginas;
    }


    public List<T> getElementen() {
        return elementen;
    }

    public long getTotaalAantalElementen() {
        return totaalAantalElementen;
    }

    public long getAantalElementenOpPagina() {
        return aantalElementenOpPagina;
    }

    public long getHuidigePagina() {
        return huidigePagina;
    }

    public long getTotaalAantalPaginas() {
        return totaalAantalPaginas;
    }
}
