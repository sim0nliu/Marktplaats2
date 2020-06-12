package marktplaats.domain;

import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GebruikerTest {

    Gebruiker testGebruiker;

    @BeforeEach
    void setup (){
        testGebruiker = new Gebruiker();
    }

    @Test
    void verifyPassword() {
    }

    @Test
    void testVerifyPassword() {
    }

    @Test
    void setPasswordThrowsAtShortPassword() {
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setWachtwoord("kort"));
    }
    @Test
    void setPasswordThrowsAtNoNumberPassword() {
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setWachtwoord("lang_zonder_nummer"));
    }
    @Test

    void setPasswordThrowsAtEmailPassword() throws InvalidEmailException {
        testGebruiker.setEmailAdress("voorbeeldemail@gmail.com");
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setWachtwoord("voorbeeldemail@gmail.com"));
    }

    @Test
    void setPasswordThrowsAtOnlyNumberPassword() {
        InvalidPasswordException e = Assertions.assertThrows(InvalidPasswordException.class, () -> testGebruiker.setWachtwoord("12345123415"));
    }

    @Test
    void checkPassword() {
    }

    @Test
    void isValidEmail() {
    }

    @Test
    void containsNONumber() {
    }

    @Test
    void verifyPasswordTest()  {
        String vbPW = "voorbeeldPassword1";
        String vbPW2 = "voorbeeldPassword2";
        try {
            testGebruiker.setWachtwoord(vbPW);
        }catch (InvalidPasswordException ex){
            assertThat("setPassword threw InvalidPasswordException").isEqualTo("which is not supposed to happen");
        }
        assertThat(testGebruiker.verifyPassword(vbPW)).isEqualTo(true);
        assertThat(testGebruiker.verifyPassword(vbPW2)).isEqualTo(false);
    }

    @Test
    void verifyPasswordTestCharArray()  {
        String vbPW = "voorbeeldPassword1";
        char[] vbPWCA= {'v','o', 'o', 'r', 'b', 'e', 'e', 'l', 'd', 'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1'};
        String vbPW2 = "voorbeeldPassword2";
        char[] vbPWCA2= {'v','o', 'o', 'r', 'b', 'e', 'e', 'l', 'd', 'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'};
        try {
            testGebruiker.setWachtwoord(vbPW);
        }catch (InvalidPasswordException ex){
            assertThat("setPassword threw InvalidPasswordException").isEqualTo("which is not supposed to happen");
        }
        assertThat(testGebruiker.verifyPassword(vbPW)).isEqualTo(true);
        assertThat(testGebruiker.verifyPassword(vbPWCA2)).isEqualTo(false);
        assertThat(testGebruiker.verifyPassword(vbPWCA)).isEqualTo(true);
    }


}
