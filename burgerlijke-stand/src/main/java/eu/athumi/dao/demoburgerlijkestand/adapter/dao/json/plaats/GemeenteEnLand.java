package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;

import java.util.Optional;

public record GemeenteEnLand(Gemeente gemeente, Land land, String buitenlandseGemeente) {
    @Override
    public String toString() {
        var gemeenteString = "";
        var landString = "";
        if (this.gemeente() != null) {
            if (this.gemeente().naam() != null) {
                gemeenteString += this.gemeente().naam();
            }
            if (this.gemeente().niscode() != null) {
                gemeenteString += " (niscode: " + this.gemeente().niscode() + ")";
            }
        }

        if (this.land() != null) {
            if (this.land().naam() != null) {
                landString += this.land().naam();
            }
            if (this.land().niscode() != null) {
                landString += " (niscode: " + this.land().niscode() + ")";
            }
        }

        var buitenlandString = Optional.ofNullable(this.buitenlandseGemeente()).orElse("");

        return String.join(" ", gemeenteString, landString, buitenlandString);
    }
}
