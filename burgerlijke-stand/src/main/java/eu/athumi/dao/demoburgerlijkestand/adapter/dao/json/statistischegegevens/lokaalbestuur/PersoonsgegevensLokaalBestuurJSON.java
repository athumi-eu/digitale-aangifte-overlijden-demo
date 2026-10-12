package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.lokaalbestuur;

public record PersoonsgegevensLokaalBestuurJSON(OudersLokaalBestuurJson ouders) {
    public record OudersLokaalBestuurJson(
            MoederOfOudsteOuderLokaalbestuurJSON moederOfOudsteOuder
    ) {}
    public record MoederOfOudsteOuderLokaalbestuurJSON(NietVerblijfshouderJson verblijfplaats) {}
    public record NietVerblijfshouderJson(Boolean nietVerblijfshouder) {}
}