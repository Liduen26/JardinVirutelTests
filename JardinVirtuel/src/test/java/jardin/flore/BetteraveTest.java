package jardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;

public class BetteraveTest {
	
	@Test
	public void testSeDuppliquer() {
		
		int longueur = 5;
		int largeur = 5;
		Betterave b = new Betterave();
		
		SimpleEntry<Integer, Integer> entry = b.seDupliquer(longueur, largeur);
		
		assertEquals(Etat.GRAINE, b.etat);
		assertTrue((entry.getKey() >= 0) && (entry.getKey() < longueur));
		assertTrue((entry.getValue() >= 0) && (entry.getValue() < largeur));
		
	}
}
