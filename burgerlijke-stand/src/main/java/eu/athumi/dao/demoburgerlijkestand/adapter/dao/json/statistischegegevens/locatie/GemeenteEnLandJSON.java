package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Gemeente;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Land;

public record GemeenteEnLandJSON(Gemeente gemeente, Land land, String buitenlandsAdres) {
    @Override
    public String toString() {
        return new GemeenteEnLand(gemeente, land, buitenlandsAdres).toString();
    }
}
