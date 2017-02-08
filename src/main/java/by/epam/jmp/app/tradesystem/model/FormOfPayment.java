package by.epam.jmp.app.tradesystem.model;

public final class FormOfPayment extends IdentifiedType {

    private final String name;
    private final int code;

    private FormOfPayment(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static final String NONE_FOP_NAME = "NONE_FOP";
    public static final int NONE_FOP_CODE = 0;

    public static final String CREDITCARD_FOP_NAME = "CREDITCARD_FOP";
    public static final int CREDITCARD_FOP_CODE = 1;

    public static final String CASH_FOP_NAME = "CASH_FOP";
    public static final int CASH_FOP_CODE = 2;

    public static final FormOfPayment NONE = new FormOfPayment(NONE_FOP_NAME, NONE_FOP_CODE);

    public static final FormOfPayment CREDITCARD = new FormOfPayment(CREDITCARD_FOP_NAME, CREDITCARD_FOP_CODE);

    public static final FormOfPayment CASH = new FormOfPayment(CASH_FOP_NAME, CASH_FOP_CODE);

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

        FormOfPayment that = (FormOfPayment) o;

        if (code != that.code) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public String toString() {
        return "FormOfPayment{" +
                "name='" + name + '\'' +
                ", code=" + code +
                "} " + super.toString();
    }

}