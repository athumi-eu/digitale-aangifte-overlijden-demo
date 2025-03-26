package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;

public record Gemeente(String niscode, String naam) {
    @Override
    public String toString() {
        var gemeenteString = "";
        if (naam != null) {
            gemeenteString += naam;
        }
        if (niscode != null) {
            gemeenteString += " (niscode: " + niscode + ")";
        }
        return gemeenteString;
    }
}
