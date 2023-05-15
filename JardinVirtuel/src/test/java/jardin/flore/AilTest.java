package jardin.flore;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AilTest {

	@Test
	public void testSeReproduireVide() {
		Ail ail = new Ail();
		HashMap<String, Integer> panier = new HashMap<String, Integer>();
		
		ail.seReproduire(panier);
		
		assertEquals(3, panier.get("Ail"));
		
	}
	
	@Test
	public void testSeReproduireNonVide() {
		Ail ail = new Ail();
		HashMap<String, Integer> panier = new HashMap<String, Integer>();
		panier.put("Ail", 2);
		
		ail.seReproduire(panier);
		
		assertEquals(5, panier.get("Ail"));
		
	}
}
