package edu.epam.webproject.entity;

import java.math.BigDecimal;

public class Vacancy extends Entity {
    private long id;
    private String logo;
    private String position;
    private String company;
    private BigDecimal salary;
    private String description;
    private VacancyStatus status;
    private User recruiter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VacancyStatus getStatus() {
        return status;
    }

    public void setStatus(VacancyStatus status) {
        this.status = status;
    }

    public User getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id &&
                logo.equals(vacancy.logo) &&
                position.equals(vacancy.position) &&
                company.equals(vacancy.company) &&
                salary.equals(vacancy.salary) &&
                description.equals(vacancy.description) &&
                status.equals(vacancy.status) &&
                recruiter.equals(vacancy.recruiter);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + logo.hashCode()
                + position.hashCode()
                + company.hashCode()
                + salary.hashCode()
                + description.hashCode()
                + status.hashCode()
                + recruiter.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vacancy { ");
        builder.append("id = ").append(id).append(", ");
        builder.append("logo = ").append(logo).append(", ");
        builder.append("position = ").append(position).append(", ");
        builder.append("company = ").append(company).append(", ");
        builder.append("salary = ").append(salary).append(", ");
        builder.append("description = ").append(description).append(", ");
        builder.append("status = ").append(status).append(", ");
        builder.append("recruiter = ").append(recruiter).append(", ");
        return builder.toString();
    }

    public enum VacancyStatus {
        RELEVANT(1),
        IRRELEVANT(2);

        private final int value;

        private VacancyStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
