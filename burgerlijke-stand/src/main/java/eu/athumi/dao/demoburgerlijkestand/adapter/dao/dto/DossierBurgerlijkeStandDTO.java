package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.BezwarenDTOType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.GeslachtDTO;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.PlaatsTypeDTO;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.RisicoDTOType;

import java.time.LocalDateTime;
import java.util.List;

public record DossierBurgerlijkeStandDTO(
    String id,
    PersoonsgegevensDTO overledene,
    PersoonsgegevensDTO huwelijkspartner,
    PersoonsgegevensDTO moeder,
    OverlijdenDTO overlijden,
    GeboorteDTO geboorte,
    BezwarenEnRisicosDTO bezwarenEnRisicos,
    VaststellingDTO vaststelling
) {
    public static class Builder {

        private String id;
        private String voornaamOverledene;
        private String naamOverledene;
        private GeslachtDTO geslacht;
        private String naamHuwelijkspartner;
        private String voornaamHuwelijkspartner;
        private AdresDTO verblijfplaats;
        private LocalDateTime tijdstipOverlijden;
        private String beschrijvingTijdstipOverlijden;
        private AdresDTO adresOverlijden;
        private PlaatsTypeDTO plaatsOverlijden;
        private List<BezwarenDTOType> bezwaren;
        private List<RisicoDTOType> risicos;
        private LocalDateTime tijdstipVaststelling;
        private String artsVanDeVaststelling;
        private PersoonsgegevensDTO moeder;
        private String beschrijvingPlaatsOverlijden;
        private GeboorteDTO geboorte;
        private String rijksregisternummer;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder rijksregisternummer(String rijksregisternummer) {
            this.rijksregisternummer = rijksregisternummer;
            return this;
        }

        public Builder beschrijvingPlaatsOverlijden(
            String beschrijvingPlaatsOverlijden
        ) {
            this.beschrijvingPlaatsOverlijden = beschrijvingPlaatsOverlijden;
            return this;
        }

        public Builder geboorte(GeboorteDTO geboorte) {
            this.geboorte = geboorte;
            return this;
        }

        public Builder voornaamOverledene(String voornaamOverledene) {
            this.voornaamOverledene = voornaamOverledene;
            return this;
        }

        public Builder naamOverledene(String naamOverledene) {
            this.naamOverledene = naamOverledene;
            return this;
        }

        public Builder geslacht(GeslachtDTO geslacht) {
            this.geslacht = geslacht;
            return this;
        }

        public Builder naamHuwelijkspartner(
            String naamHuwelijkspartner
        ) {
            this.naamHuwelijkspartner = naamHuwelijkspartner;
            return this;
        }

        public Builder voornaamHuwelijkspartner(
            String voornaamHuwelijkspartner
        ) {
            this.voornaamHuwelijkspartner = voornaamHuwelijkspartner;
            return this;
        }

        public Builder verblijfplaats(AdresDTO verblijfplaats) {
            this.verblijfplaats = verblijfplaats;
            return this;
        }

        public Builder tijdstipOverlijden(
            LocalDateTime tijdstipOverlijden
        ) {
            this.tijdstipOverlijden = tijdstipOverlijden;
            return this;
        }

        public Builder adresOverlijden(AdresDTO adresOverlijden) {
            this.adresOverlijden = adresOverlijden;
            return this;
        }

        public Builder plaatsOverlijden(PlaatsTypeDTO plaatsOverlijden) {
            this.plaatsOverlijden = plaatsOverlijden;
            return this;
        }

        public Builder bezwaren(List<BezwarenDTOType> bezwaren) {
            this.bezwaren = bezwaren;
            return this;
        }

        public Builder risicos(List<RisicoDTOType> risicos) {
            this.risicos = risicos;
            return this;
        }

        public Builder tijdstipVaststelling(
            LocalDateTime tijdstipVaststelling
        ) {
            this.tijdstipVaststelling = tijdstipVaststelling;
            return this;
        }

        public Builder artsVanDeVaststelling(
            String artsVanDeVaststelling
        ) {
            this.artsVanDeVaststelling = artsVanDeVaststelling;
            return this;
        }

        public Builder beschrijvingTijdstipOverlijden(
            String beschrijvingTijdstipOverlijden
        ) {
            this.beschrijvingTijdstipOverlijden = beschrijvingTijdstipOverlijden;
            return this;
        }

        public Builder moeder(PersoonsgegevensDTO moeder) {
            this.moeder = moeder;
            return this;
        }

        public DossierBurgerlijkeStandDTO build() {
            return new DossierBurgerlijkeStandDTO(
                    id,
                new PersoonsgegevensDTO(
                    voornaamOverledene,
                    naamOverledene,
                    geslacht,
                    verblijfplaats,
                    rijksregisternummer
                ),
                new PersoonsgegevensDTO.Builder()
                    .voornaam(voornaamHuwelijkspartner)
                    .naam(naamHuwelijkspartner)
                    .build(),
                moeder,
                new OverlijdenDTO(
                    tijdstipOverlijden,
                    beschrijvingTijdstipOverlijden,
                    new PlaatsDTO(adresOverlijden, plaatsOverlijden, beschrijvingPlaatsOverlijden)
                ),
                geboorte,
                new BezwarenEnRisicosDTO(bezwaren, risicos),
                new VaststellingDTO(tijdstipVaststelling, artsVanDeVaststelling)
            );
        }
    }
}
