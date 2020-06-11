package marktplaats.domain;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@NoArgsConstructor
public class Gebruikers {
    List<Gebruiker> gebruikers = new ArrayList<>();

    private Gebruikers(List<Gebruiker> students) {
        this.gebruikers.addAll(gebruikers);
    }

    public static Gebruikers of(List<Gebruiker> list) {
        return new Gebruikers(list);
    }

    // For JAXB (Java bean standard)
    public List<Gebruiker> getStudents() {
        return gebruikers;
    }
}
