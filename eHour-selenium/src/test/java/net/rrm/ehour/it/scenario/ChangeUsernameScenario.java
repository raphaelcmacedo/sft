package net.rrm.ehour.it.scenario;

import net.rrm.ehour.it.AbstractScenario;
import org.junit.Test;

import static net.rrm.ehour.it.driver.EhourApplicationDriver.loginAdmin;
import static net.rrm.ehour.it.driver.UserManagementDriver.*;

// EHO-339
public class ChangeUsernameScenario extends AbstractScenario {
    @Test
    public void should_change_username() {
        String lastName = "Edeling2";

        loginAdmin();

        loadUserAdmin();
        createUser(new ItUser("thies2", "a"), lastName, "User");
        assertUserDataSaved();

        loadUserAdmin();
        showOnlyActiveUsers();
        editUser(lastName);

        setFormFieldTo("username", "thies3");
        submitUserForm();

        assertUserDataSaved();
    }
}
