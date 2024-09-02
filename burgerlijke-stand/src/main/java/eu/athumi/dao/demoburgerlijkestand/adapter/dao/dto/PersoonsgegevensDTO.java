package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.GeslachtDTO;

public record PersoonsgegevensDTO(
    String voornaam,
    String naam,
    GeslachtDTO geslacht,
    AdresDTO verblijfplaats,
    String rijksregisternummer
) {
    public static class Builder {

        private String voornaam;
        private String naam;
        private GeslachtDTO geslacht;
        private AdresDTO verblijfplaats;
        private String rijksregisternummer;

        public Builder voornaam(String voornaam) {
            this.voornaam = voornaam;
            return this;
        }

        public Builder naam(String naam) {
            this.naam = naam;
            return this;
        }

        public Builder geslacht(GeslachtDTO geslacht) {
            this.geslacht = geslacht;
            return this;
        }

        public Builder verblijfplaats(AdresDTO verblijfplaats) {
            this.verblijfplaats = verblijfplaats;
            return this;
        }

        public Builder rijksregisternummer(String rijksregisternummer) {
            this.rijksregisternummer = rijksregisternummer;
            return this;
        }

        public PersoonsgegevensDTO build() {
            return new PersoonsgegevensDTO(
                voornaam,
                naam,
                geslacht,
                verblijfplaats,
                rijksregisternummer
            );
        }
    }
}
