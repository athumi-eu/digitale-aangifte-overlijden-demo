package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

import java.util.List;

public class PageableResultJSON<T> {


    private List<T> elementen;

    private Long totaalAantalElementen;
    private Long totaalAantalPaginas;
    private Long huidigePagina;
    private Long aantalElementenOpPagina;

    public PageableResultJSON() {}

    public PageableResultJSON(
            List<T> elementen,
            Long totaalAantalElementen,
            Long huidigePagina,
            Long aantalElementenOpPagina,
            Long aantalPaginas
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

    public Long getTotaalAantalElementen() {
        return totaalAantalElementen;
    }

    public Long getAantalElementenOpPagina() {
        return aantalElementenOpPagina;
    }

    public Long getHuidigePagina() {
        return huidigePagina;
    }

    public Long getTotaalAantalPaginas() {
        return totaalAantalPaginas;
    }
}
