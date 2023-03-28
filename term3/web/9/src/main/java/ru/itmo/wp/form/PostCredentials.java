package ru.itmo.wp.form;


import javax.validation.constraints.*;

public class PostCredentials {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    @NotNull
    @NotBlank
    @NotBlank
    @Size(min = 2, max = 600)
    @Pattern(regexp = "[a-zA-z\\s]*", message = "Expected lowercase Latin letters")
    private String tags;

    @NotNull
    @NotBlank
    @NotBlank
    @Size(min = 2, max = 6000)
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
