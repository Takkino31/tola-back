package esp.dgi.tola.dtos;

import java.util.Date;

public class QuestionResponseDto {
    private Long id;
    private String title;
    private String question;
    private Date dateAsked;
    private String username;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDateAsked() {
        return dateAsked;
    }

    public void setDateAsked(Date dateAsked) {
        this.dateAsked = dateAsked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
