package jardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;

public class BetteraveTest {
	
	@Test
	public void testSeDupliquer() {
		//Arrange
		Betterave betterave = new Betterave();
		betterave.grandir();
		
		int longueur = 5;
		int largeur = 3;
		
		//Act
		SimpleEntry<Integer, Integer> result = betterave.seDupliquer(longueur, largeur);
		int resultX = result.getKey();
		int resultY = result.getValue();
		
		//Assert
		assertEquals(Etat.GRAINE, betterave.getEtat());
		assertTrue(resultX >= 0 && resultX < longueur);
		assertTrue(resultY >= 0 && resultY < largeur);		
	}
	

}
