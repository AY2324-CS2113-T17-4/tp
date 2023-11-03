package seedu.duke.models.logic;

import org.junit.jupiter.api.Test;
import seedu.duke.models.schema.ModuleList;
import seedu.duke.models.schema.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.controllers.ModuleMethodsController.showModulesLeft;
import static seedu.duke.models.logic.DataRepository.getRequirements;

class ModulesLeftTest {

    //success scenario: difference (not working)
    /*
    @Test
    void listModulesLeftTest_expectArrayList() {
        Student student = new Student();

        ModuleList modulesTaken = new ModuleList("CG1111A CS1231 MA1511 MA1512 CS1010 GESS1000 GEC1000 " +
                "GEN2000 ES2631 GEA1000 DTK1234 EG1311 IE2141");

        student.setMajor("CEG");
        ModuleList modulesRequiredForMajor = new ModuleList(student.getMajorModuleCodes());
//wrong
        ModulesLeft modulesLeft = new ModulesLeft(modulesRequiredForMajor, modulesTaken);

        ModuleList expectedModulesLeft = new ModuleList("EE2211 EG2501 CDE2000 PF1101 CG4002 MA1508E " +
                "EG2401A CP3880 CG2111A CG2023 CG2027 CG2028 CG2271 ST2334 CS2040C CS2113 EE2026 EE4204");


        assertEquals(expectedModulesLeft.getModuleCodes(), student.getModuleCodesLeft());
    }

     */
}
