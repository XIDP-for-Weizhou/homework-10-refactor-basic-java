package practice1;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jxzhong on 2017/8/28.
 */
public class SecurityTest {


    private Security security;
    private SecurityChecker checker;

    @Before
    public void setUp() {
        checker = mock(SecurityChecker.class);
        security = new Security(checker);
    }

    @Test
    public void should_get_false_when_user_is_null() {
        //Given
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(new Permission()).build();
        Permission currentPermission = new Permission();
        User user = null;

        //Then
        assertFalse(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_false_when_permission_is_null() {
        //Given
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(new Permission()).build();
        Permission currentPermission = null;
        User user = new User();

        //Then
        assertFalse(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_false_when_permissions_is_empty() {
        //Given
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().build();
        Permission currentPermission = new Permission();
        User user = new User();

        //Then
        assertFalse(security.hasAccess(user, currentPermission, permissions));
    }


    @Test
    public void should_get_false_when_permissions_not_include_input_permission_and_check_current_permission_false() {
        //Given
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().build();
        Permission currentPermission = new Permission();
        User user = new User();

        //When
        when(checker.checkPermission(user, currentPermission )).thenReturn(false);

        //Then
        assertFalse(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_true_when_permissions_not_include_input_permission_and_check_current_permission_true() {
        //Given
        Permission currentPermission = new Permission();
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(new Permission()).build();
        User user = new User();

        //When
        when(checker.checkPermission(user, currentPermission )).thenReturn(true);

        //Then
        assertTrue(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_true_when_permissions_include_input_permission_and_check_current_permission_false() {
        Security security = new Security(checker);
        Permission currentPermission = new Permission();
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(currentPermission).build();
        User user = new User();

        //When
        when(checker.checkPermission(user, currentPermission )).thenReturn(false);

        //Then
        assertTrue(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_true_when_user_is_admin() {
        //Given
        Permission currentPermission = new Permission();
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(new Permission()).build();
        User user = new User();

        //When
        when(checker.checkPermission(user, currentPermission )).thenReturn(false);
        when(checker.isAdmin()).thenReturn(true);

        //Then
        assertTrue(security.hasAccess(user, currentPermission, permissions));
    }

    @Test
    public void should_get_true_when_user_is_not_admin() {
        //Given
        Permission currentPermission = new Permission();
        ImmutableList<Permission> permissions = ImmutableList.<Permission>builder().add(new Permission()).build();
        User user = new User();

        //When
        when(checker.checkPermission(user, currentPermission )).thenReturn(false);
        when(checker.isAdmin()).thenReturn(false);

        //Then
        assertFalse(security.hasAccess(user, currentPermission, permissions));
    }


}