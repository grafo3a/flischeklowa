package net.apasajb.flischeklowa.outils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestValidation {
	
	
	@Test
	void testerValidationCourriel() {
		
		Assertions.assertTrue(Validation.validerCourriel("joseph55@flischeklowa.net"));
		Assertions.assertTrue(Validation.validerCourriel("joseph.55@flischeklowa.net"));
		Assertions.assertTrue(Validation.validerCourriel("joseph_55@flischeklowa.net"));
		Assertions.assertTrue(Validation.validerCourriel("joseph-55@flischeklowa.net"));
		Assertions.assertTrue(Validation.validerCourriel("55joseph@flischeklowa.net"));
		Assertions.assertTrue(Validation.validerCourriel("joseph_55_56@fk.net"));
		Assertions.assertFalse(Validation.validerCourriel("joseph55@fknet"));
		Assertions.assertFalse(Validation.validerCourriel("_joseph55@fk.net"));
		Assertions.assertFalse(Validation.validerCourriel("joseph_55_@fk.net"));
		Assertions.assertFalse(Validation.validerCourriel("joseph_.55@fk.net"));
		Assertions.assertFalse(Validation.validerCourriel("joseph55_@fk.net"));
	}
}
