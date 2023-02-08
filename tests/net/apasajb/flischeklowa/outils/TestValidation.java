package net.apasajb.flischeklowa.outils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestValidation {
	
	Validation validation = new ValidationImple();
	
	@Test
	void testerValidationCourriel() {
		
		Assertions.assertTrue(validation.isCourrielValid("joseph55@flischeklowa.net"));
		Assertions.assertTrue(validation.isCourrielValid("joseph.55@flischeklowa.net"));
		Assertions.assertTrue(validation.isCourrielValid("joseph_55@flischeklowa.net"));
		Assertions.assertTrue(validation.isCourrielValid("joseph-55@flischeklowa.net"));
		Assertions.assertTrue(validation.isCourrielValid("55joseph@flischeklowa.net"));
		Assertions.assertTrue(validation.isCourrielValid("joseph_55_56@fk.net"));
		Assertions.assertFalse(validation.isCourrielValid("joseph55@fknet"));
		Assertions.assertFalse(validation.isCourrielValid("_joseph55@fk.net"));
		Assertions.assertFalse(validation.isCourrielValid("joseph_55_@fk.net"));
		Assertions.assertFalse(validation.isCourrielValid("joseph_.55@fk.net"));
		Assertions.assertFalse(validation.isCourrielValid("joseph55_@fk.net"));
	}
}
