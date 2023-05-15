package jardin.flore;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CarotteTest {

	@Test
	public void testSeReproduireVide() {
		// Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		IRacePure carotte = new Carotte();
		int expected = 3;

		// Act
		carotte.seReproduire(panier);

		// Assert
		assertEquals(expected, panier.get("Carotte"));
	}

	@Test
	public void testSeReproduireNonVide() {
		// Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		panier.put("Carotte", 2);
		IRacePure carotte = new Carotte();
		int expected = 5;

		// Act
		carotte.seReproduire(panier);

		// Assert
		assertEquals(expected, panier.get("Carotte"));
	}

}
