package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.PlaatsTypeDTO;

public record PlaatsDTO(AdresDTO adres, PlaatsTypeDTO type, String beschrijving) {
    public static class Builder {

        private AdresDTO adres;
        private PlaatsTypeDTO type;
        private String beschrijving;

        public Builder adres(AdresDTO adres) {
            this.adres = adres;
            return this;
        }

        public Builder type(PlaatsTypeDTO type) {
            this.type = type;
            return this;
        }

        public Builder beschrijving(String beschrijving) {
            this.beschrijving = beschrijving;
            return this;
        }

        public PlaatsDTO build() {
            return new PlaatsDTO(adres, type, beschrijving);
        }
    }
}
