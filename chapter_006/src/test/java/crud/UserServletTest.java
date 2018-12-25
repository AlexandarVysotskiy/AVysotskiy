package crud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {

    private Validate validate = new ValidateStub();

    @Before
    public void addUser() throws ServletException, IOException {
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Test");
        when(req.getParameter("login")).thenReturn("Test");
        when(req.getParameter("email")).thenReturn("test@mail.ru");
        when(req.getParameter("password")).thenReturn("qwerty");
        when(req.getParameter("role")).thenReturn("admin");
        new UserCreateServlet().doPost(req, resp);
    }

    @Test
    public void createUserTest() {
        assertThat(validate.getAll().iterator().next().getName(), is("Test"));
        assertThat(validate.getAll().iterator().next().getLogin(), is("Test"));
        assertThat(validate.getAll().iterator().next().getEmail(), is("test@mail.ru"));
        assertThat(validate.getAll().iterator().next().getPassword(), is("qwerty"));
        assertThat(validate.getAll().iterator().next().getRole(), is(Role.admin));
    }

    @Test
    public void deleteUserTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        assertTrue(validate.getAll().iterator().hasNext());
        new UsersController().doPost(req, resp);
        assertFalse(validate.getAll().iterator().hasNext());
    }

    @Test
    public void updateUserTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("UpdateName");
        when(req.getParameter("login")).thenReturn("UpdateLogin");
        when(req.getParameter("email")).thenReturn("updateMail@mail.ru");
        when(req.getParameter("password")).thenReturn("UpdatePassword");
        when(req.getParameter("role")).thenReturn("user");
        assertThat(validate.getAll().iterator().next().getName(), is("Test"));
        assertThat(validate.getAll().iterator().next().getLogin(), is("Test"));
        assertThat(validate.getAll().iterator().next().getEmail(), is("test@mail.ru"));
        assertThat(validate.getAll().iterator().next().getPassword(), is("qwerty"));
        assertThat(validate.getAll().iterator().next().getRole(), is(Role.admin));
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.getAll().iterator().next().getName(), is("UpdateName"));
        assertThat(validate.getAll().iterator().next().getLogin(), is("UpdateLogin"));
        assertThat(validate.getAll().iterator().next().getEmail(), is("updateMail@mail.ru"));
        assertThat(validate.getAll().iterator().next().getPassword(), is("UpdatePassword"));
        assertThat(validate.getAll().iterator().next().getRole(), is(Role.user));
    }
}