package by.epam.jmp.app.tradesystem.model;

public abstract class UserCompany extends User {

    private Company company;

    public UserCompany(String username, String password, UserRole userRole) {
        super(username, password, userRole);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserCompany that = (UserCompany) o;

        return company != null ? company.equals(that.company) : that.company == null;
    }

}