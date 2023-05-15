package jardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VegetalTest {

	@Test
	public void testGrandir() { 
		//Arrange
		Vegetal v = new Vegetal() {		};
		Etat[] expectedEtats = {Etat.GRAINE, Etat.GERME, Etat.TIGE, Etat.FEUILLE, Etat.FLEUR, Etat.MORT};
		
		for(int i=1; i< expectedEtats.length; i++) {
			//Act
			v.grandir();
			//Assert
			assertEquals(expectedEtats[i], v.getEtat());				
		}
	}
	
	
}
