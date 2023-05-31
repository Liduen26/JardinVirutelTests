package jardin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jardin.flore.Ail;
import jardin.flore.Betterave;
import jardin.flore.Carotte;
import jardin.flore.Etat;

@ExtendWith(MockitoExtension.class)
public class JardinTest {

	@Mock
	private InputReader inputReaderMock;

	@Mock
	private Betterave betteraveMock;

	private Jardin j;

	@BeforeEach
	private void init() {
		j = new Jardin(3, 4);
	}

	@Test
	public void testSemerAil() {
		// Arrange
//		String input = "0 0 1";
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
		j.setInputReader(inputReaderMock);

		when(inputReaderMock.readIntValue()).thenReturn(0, 0, 1);

		j.ajouterPanier("Ail", 3);

		// Act
		j.semer();

		// Assert
		verify(inputReaderMock, times(3)).readIntValue();

		assertEquals(2, j.getPanier().get("Ail"));
		assertTrue(j.getEmplacement()[0][0].getVeg() instanceof Ail);
	}

	@Test
	public void testAjouterPanierVide() {
		// Arrange
		int expectedQuantite = 3;
		String vegetalName = "Ail";

		// Act
		j.ajouterPanier(vegetalName, 3);

		// Assert
		assertEquals(expectedQuantite, j.getPanier().get(vegetalName));
	}

	@Test
	public void testAjouterPanierNonVide() {
		// Arrange
		int expectedQuantite = 5;
		String vegetalName = "Ail";

		// Act
		j.ajouterPanier(vegetalName, 2);
		j.ajouterPanier(vegetalName, 3);

		// Assert
		assertEquals(expectedQuantite, j.getPanier().get(vegetalName));
	}

	@Test
	public void testPasserSaisonSuivanteNonMort() {
		// Arrange
		j.getEmplacement()[0][0] = new Emplacement(new Ail());

		// Act
		j.passerSaisonSuivante();
		Etat result = j.getEmplacement()[0][0].getVeg().getEtat();

		// Assert
		assertEquals(Etat.GERME, result);
	}

	@Test
	public void testPasserSaisonSuivantMort() {
		// Arrange
		j.getEmplacement()[0][0] = new Emplacement(new Ail());

		// Act
		for (int i = 0; i < 6; i++) {
			j.passerSaisonSuivante();
		}

		// Assert
		assertNull(j.getEmplacement()[0][0]);
	}

	@Test
	public void testRecolterEnFleurEtOgm() {
		//Arrange
		when(betteraveMock.getEtat()).thenReturn(Etat.FLEUR);		
		SimpleEntry<Integer, Integer> coords = new SimpleEntry<>(1,1);
		when(betteraveMock.seDupliquer(3, 4)).thenReturn(coords);
		
		j.getEmplacement()[0][0] = new Emplacement(betteraveMock);
		
		//Act
		j.recolter();
		
		//Assert
		verify(betteraveMock).getEtat();
		verify(betteraveMock).seDupliquer(3, 4);
		
		assertNull(j.getEmplacement()[0][0]);
		assertNotNull(j.getEmplacement()[1][1]);		
	}
	
	@Test
	public void testToutCramer() {
		// Arrange
		j.getEmplacement()[0][1] = new Emplacement(new Ail());
		j.getEmplacement()[0][2] = new Emplacement(new Carotte());
		
		// Act
		j.toutCramer();
		
		// Assert
		assertNull(j.getEmplacement()[0][1]);
		assertNull(j.getEmplacement()[0][2]);
	}

}
