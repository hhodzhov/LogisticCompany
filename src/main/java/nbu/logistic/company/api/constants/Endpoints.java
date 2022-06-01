package nbu.logistic.company.api.constants;

public class Endpoints {

    private Endpoints() {
        throw new IllegalStateException("Utility class!");
    }

    public static final String ROOT = "/api";
    public static final String ROLE_CREATE = "/admin/role/create";
    public static final String ADD_ROLE_TO_USER = "/admin/role/add-to-user";
    public static final String TOKEN_REFRESH = "/token/refresh";
    public static final String CLIENT_REGISTER = "/register-client";


    public static final String LOGISTIC_COMPANY_UPDATE = "/admin/logistic-company/{id}/update";
    public static final String LOGISTIC_COMPANIES_GET = "/admin/logistic-companies";
    public static final String LOGISTIC_COMPANY_DELETE = "/admin/logistic-company/{id}/delete";
    public static final String LOGISTIC_COMPANY_CREATE = "/admin/logistic-company/create";

    public static final String USER_UPDATE = "/admin/user/update";
    public static final String USERS_GET = "/admin/users";
    public static final String USER_DELETE = "/admin/user/{username}/delete";
    public static final String USER_CREATE = "/admin/user/create";

    public static final String OFFICE_UPDATE = "/admin/office/{id}/update";
    public static final String OFFICE_GET = "/admin/offices";
    public static final String OFFICE_DELETE = "/admin/offices/{id}/delete";
    public static final String OFFICES_CREATE = "/admin/office/create";

    public static final String SHIPMENT_UPDATE = "/agent/shipment/{id}/update";
    public static final String SHIPMENT_CREATE = "/agent/shipment/create";
    public static final String SHIPMENT_GET = "/agent/shipments";
    public static final String SHIPMENT_DELETE = "/agent/shipment/{id}/delete";

}
