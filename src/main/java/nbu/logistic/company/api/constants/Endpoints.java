package nbu.logistic.company.api.constants;

public class Endpoints {

    private Endpoints() {
        throw new IllegalStateException("Utility class!");
    }

    public static final String ROOT = "/api";
    public static final String USERS = "/users";
    public static final String USER_SAVE = "/user/save";
    public static final String ROLE_SAVE = "/role/save";
    public static final String ADD_ROLE_TO_USER = "/admin/role/add-to-user";
    public static final String TOKEN_REFRESH = "/token/refresh";
    public static final String CLIENT_REGISTER = "/client/register";
}
