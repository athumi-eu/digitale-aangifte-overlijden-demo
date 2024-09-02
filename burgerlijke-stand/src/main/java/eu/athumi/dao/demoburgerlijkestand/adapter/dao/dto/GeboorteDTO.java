package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;

import java.time.LocalDateTime;

public record GeboorteDTO(
    LocalDateTime tijdstip,
    Boolean doodgeboorte,
    PlaatsDTO plaats,
    Boolean uitMeervoudigeZwangerschap,
    MeervoudigeZwangerschapDTO meervoudigeZwangerschap
) {
    public static class Builder {

        LocalDateTime tijdstip;
        Boolean doodgeboorte;
        PlaatsDTO plaats;
        Boolean uitMeervoudigeZwangerschap;
        MeervoudigeZwangerschapDTO meervoudigeZwangerschap;

        public Builder tijdstip(LocalDateTime tijdstip) {
            this.tijdstip = tijdstip;
            return this;
        }

        public Builder doodgeboorte(Boolean doodgeboorte) {
            this.doodgeboorte = doodgeboorte;
            return this;
        }

        public Builder plaats(PlaatsDTO plaats) {
            this.plaats = plaats;
            return this;
        }

        public Builder uitMeervoudigeZwangerschap(Boolean uitMeervoudigeZwangerschap) {
            this.uitMeervoudigeZwangerschap = uitMeervoudigeZwangerschap;
            return this;
        }

        public Builder meervoudigeZwangerschap(MeervoudigeZwangerschapDTO meervoudigeZwangerschap) {
            this.meervoudigeZwangerschap = meervoudigeZwangerschap;
            return this;
        }

        public GeboorteDTO build() {
            return new GeboorteDTO(
                tijdstip,
                doodgeboorte,
                plaats,
                uitMeervoudigeZwangerschap,
                meervoudigeZwangerschap
            );
        }
    }
}
