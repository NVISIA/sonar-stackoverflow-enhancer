package com.see.app.api;

public class ApiObject {

    public String issue;
    public String result;
    public String link;
    public String title;
    public int score;

    public ApiObject() {super(); }

    public ApiObject(String issue)
    {
        super();
        this.issue = issue;
    }

    public ApiObject(String issue, String result, String link, String title, int score) {
        this.issue = issue;
        this.result = result;
        this.link = link;
        this.title = title;
        this.score = score;
    }

    public String getIssue() { return issue;}

    public void setResult(String result) { this.result = result; }

    public String getResult() {
        return result;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {

        return "Issue: " + getIssue() + " Link: " + getLink() + " Score:" + getScore();
    }
}
