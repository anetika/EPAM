package edu.epam.webproject.entity;

import java.sql.Date;

public class Feedback extends Entity {
    private long id;
    private User employee;
    private Vacancy vacancy;
    private String letter;
    private FeedbackStatus status;
    private Date date;

    public Feedback(){}

    public Feedback(long id, User employee, Vacancy vacancy, String letter, FeedbackStatus status, Date date) {
        this.id = id;
        this.employee = employee;
        this.vacancy = vacancy;
        this.letter = letter;
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                employee.equals(feedback.employee) &&
                vacancy.equals(feedback.vacancy) &&
                letter.equals(feedback.letter) &&
                date.equals(feedback.date) &&
                status == feedback.status;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + employee.hashCode()
                + vacancy.hashCode()
                + letter.hashCode()
                + status.hashCode()
                + date.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Feedback{");
        builder.append("id = ").append(id).append(", ");
        builder.append("employee = ").append(employee).append(", ");
        builder.append("vacancy = ").append(vacancy).append(", ");
        builder.append("letter = ").append(letter).append(", ");
        builder.append("status = ").append(status).append(", ");
        builder.append("date = ").append(date).append(" ");
        builder.append("}");
        return builder.toString();
        
    }

    public enum FeedbackStatus {
        RELEVANT(1),
        IRRELEVANT(2),
        IN_PROCESS(3);

        private final int value;
        FeedbackStatus(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
