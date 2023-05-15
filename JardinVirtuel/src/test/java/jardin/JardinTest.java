package jardin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

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
	Betterave betterave;
	
	@Mock
	Carotte carotte;
	
	private Jardin j;
	
	@BeforeEach
	void init() {
		j = new Jardin(5,5);
	}
	
	void testAjouterPanier(){
		String nom = "nom";
		Integer quantite = 18;
		j.ajouterPanier(nom, quantite);
		
		
		// Pas fini ¯\_(ツ)_/¯
	}
	
	
	@Test
	void testPasserSaisonSuivante() {
		// Pour chaque emplacement s'il y a qqchose : 
		// Si mort on l'enlève (null)
		// Si pas mort grandis
		
		// Plante de l'ail en 1;1
		j.getEmplacement()[1][1] = new Emplacement(new Ail());
		
		// Plante de l'ail en 2;1 et le fait mourir
		j.getEmplacement()[2][1] = new Emplacement(new Ail());
		j.getEmplacement()[2][1].getVeg().grandir(5);
		
		j.passerSaisonSuivante();
		
		assertEquals(j.getEmplacement()[1][1].getVeg().getEtat(), Etat.GERME);
		assertNull(j.getEmplacement()[2][1]);
		assertNull(j.getEmplacement()[3][1]);
	}
	
	@Test
	void testRecolter() {
		// Pour chaque emplacement s'il y a qqchose et si c'est une fleur
		// On l'enlève (null)
		// Reproduis si race pure, duplique si Ogms
		
		HashMap<String, Integer> panier = j.getPanier();
		
		// Plante de l'ail en 1;1
		j.getEmplacement()[1][1] = new Emplacement(new Ail());
		
		// Plante de l'ail en 2;1 et le fait fleurir
		j.getEmplacement()[2][1] = new Emplacement(new Ail());
		j.getEmplacement()[2][1].getVeg().grandir(4);
		
		j.passerSaisonSuivante();
		
		assertNull(j.getEmplacement()[1][1]);
		assertNull(j.getEmplacement()[2][1]);
		assertEquals(j.getEmplacement()[2][1].getVeg().getEtat(), Etat.GERME);
	}
	
	
	// Test unitaire
	@Test 
	void testSemerAil() {
		// Arrange
		String input = "0 0 1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		// Act
		j.semer();
		
		// Assert
		assertEquals(2,  j.getPanier().get("Ail"));
		assertTrue(j.getEmplacement()[0][0].getVeg() instanceof Ail);
	}
	
	
	// Test Mockito
	@Test
	void testSemerAilMock() {
		// Arrange
		j.setInputReader(inputReaderMock);
		
		when(inputReaderMock.readIntValue()).thenReturn(1);
		
		// Act
		j.semer();
		
		// Assert
		verify(inputReaderMock, times(3)).readIntValue();
		
		assertEquals(2,  j.getPanier().get("Ail"));
		assertTrue(j.getEmplacement()[0][0].getVeg() instanceof Ail);
	}
	
	
	// Test Mockito
	@Test
	void testRecolterNEnFleurEtOgm() {
		// Arrange
		j.getEmplacement()[3][3] = new Emplacement(betterave);
		
		when(betterave.seDupliquer(5, 5)).thenReturn(new SimpleEntry<Integer,Integer>(4, 4));
		when(betterave.getEtat()).thenReturn(Etat.FLEUR);
		
		// Act
		j.recolter();
		
		// Assert
		verify(betterave, times(2)).seDupliquer(5, 5);
		verify(betterave, times(2)).getEtat();
		
		assertNull(j.getEmplacement()[3][3]);
		assertTrue(j.getEmplacement()[4][4].getVeg() instanceof Betterave);
	}
	
}
