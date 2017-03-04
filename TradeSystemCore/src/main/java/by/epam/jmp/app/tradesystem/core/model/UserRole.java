package by.epam.jmp.app.tradesystem.core.model;

public final class UserRole extends IdentifiedType {

    private final String name;
    private final int code;

    private UserRole(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static final String VENDOR_NAME = "VENDOR_ROLE";
    public static final int VENDOR_CODE = 0;

    public static final String CUSTOMER_NAME = "CUSTOMER_ROLE";
    public static final int CUSTOMER_CODE = 1;

    public static final String DELIVERY_NAME = "DELIVERY_ROLE";
    public static final int DELIVERY_CODE = 2;

    public static final String ADMIN_NAME = "ADMIN_ROLE";
    public static final int ADMIN_CODE = 3;

    public static final UserRole VENDOR = new UserRole(VENDOR_NAME, VENDOR_CODE);

    public static final UserRole CUSTOMER = new UserRole(CUSTOMER_NAME, CUSTOMER_CODE);

    public static final UserRole DELIVERY = new UserRole(DELIVERY_NAME, DELIVERY_CODE);

    public static final UserRole ADMIN = new UserRole(ADMIN_NAME, ADMIN_CODE);

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return code == userRole.code && (name != null ? name.equals(userRole.name) : userRole.name == null);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "name='" + name + '\'' +
                ", code=" + code +
                "} " + super.toString();
    }

}