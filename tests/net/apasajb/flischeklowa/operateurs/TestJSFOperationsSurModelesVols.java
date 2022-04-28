package net.apasajb.flischeklowa.operateurs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class TestJSFOperationsSurModelesVols {
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {}
	
	
	@Test
	void controlerModelesVolsAller() {
		
		JSFOperationsSurModelesVols modele = new JSFOperationsSurModelesVols();
		
		modele.lireModeleVol("09H00");
		Assertions.assertEquals("Berlin", modele.getVille());
		
		modele.lireModeleVol("10H00");
		Assertions.assertEquals("Lisbon", modele.getVille());
		
		modele.lireModeleVol("11H00");
		Assertions.assertEquals("London", modele.getVille());
		
		modele.lireModeleVol("12H00");
		Assertions.assertEquals("Paris", modele.getVille());
		
		modele.lireModeleVol("13H00");
		Assertions.assertEquals("Vienna", modele.getVille());
		
		modele.lireModeleVol("14H00");
		Assertions.assertEquals("Roma", modele.getVille());
		
		modele.lireModeleVol("15H00");
		Assertions.assertEquals("Geneva", modele.getVille());
		
		modele.lireModeleVol("16H00");
		Assertions.assertEquals("Madrid", modele.getVille());
		
		modele.lireModeleVol("17H00");
		Assertions.assertEquals("Brussels", modele.getVille());
		
		modele.lireModeleVol("18H00");
		Assertions.assertEquals("Amsterdam", modele.getVille());
		
		modele.lireModeleVol("19H00");
		Assertions.assertEquals("Copenhagen", modele.getVille());
		
		modele.lireModeleVol("20H00");
		Assertions.assertEquals("Gdansk", modele.getVille());
		
		modele.lireModeleVol("21H00");
		Assertions.assertEquals("Prague", modele.getVille());
		
		modele.lireModeleVol("22H00");
		Assertions.assertEquals("Frankfurt-AM", modele.getVille());
		
		modele.lireModeleVol("23H00");
		Assertions.assertEquals("Cracow", modele.getVille());
	}
	
	
	@Test
	void controlerModelesVolsRetour() {
		
		JSFOperationsSurModelesVols modele = new JSFOperationsSurModelesVols();
		
		modele.lireModeleVol("09H30");
		Assertions.assertEquals("Madrid", modele.getVille());
		
		modele.lireModeleVol("10H30");
		Assertions.assertEquals("Brussels", modele.getVille());
		
		modele.lireModeleVol("11H30");
		Assertions.assertEquals("Amsterdam", modele.getVille());
		
		modele.lireModeleVol("12H30");
		Assertions.assertEquals("Copenhagen", modele.getVille());
		
		modele.lireModeleVol("13H30");
		Assertions.assertEquals("Gdansk", modele.getVille());
		
		modele.lireModeleVol("14H30");
		Assertions.assertEquals("Prague", modele.getVille());
		
		modele.lireModeleVol("15H30");
		Assertions.assertEquals("Frankfurt-AM", modele.getVille());
		
		modele.lireModeleVol("16H30");
		Assertions.assertEquals("Cracow", modele.getVille());
		
		modele.lireModeleVol("17H30");
		Assertions.assertEquals("Berlin", modele.getVille());
		
		modele.lireModeleVol("18H30");
		Assertions.assertEquals("Lisbon", modele.getVille());
		
		modele.lireModeleVol("19H30");
		Assertions.assertEquals("London", modele.getVille());
		
		modele.lireModeleVol("20H30");
		Assertions.assertEquals("Paris", modele.getVille());
		
		modele.lireModeleVol("21H30");
		Assertions.assertEquals("Vienna", modele.getVille());
		
		modele.lireModeleVol("22H30");
		Assertions.assertEquals("Roma", modele.getVille());
		
		modele.lireModeleVol("23H30");
		Assertions.assertEquals("Geneva", modele.getVille());
	}
}
