package jardin.flore;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AilTest {

	@Test
	public void testSeReproduireVide() {
		// Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		IRacePure ail = new Ail();
		int expected = 3;

		// Act
		ail.seReproduire(panier);

		// Assert
		assertEquals(expected, panier.get("Ail"));
	}

	@Test
	public void testSeReproduireNonVide() {
		// Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		panier.put("Ail", 2);
		IRacePure ail = new Ail();
		int expected = 5;

		// Act
		ail.seReproduire(panier);

		// Assert
		assertEquals(expected, panier.get("Ail"));
	}

}
