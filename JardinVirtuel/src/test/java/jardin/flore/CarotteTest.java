package jardin.flore;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CarotteTest {
	
	@Test
	public void testSeReproduire() {
		Carotte c = new Carotte();
		HashMap<String, Integer> panier = new HashMap<String, Integer>();
		panier.put("Carotte", 2);
		
		c.seReproduire(panier);
		
		assertEquals(5, panier.get("Carotte"));
		
	}
}
