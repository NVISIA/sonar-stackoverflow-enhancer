package com.see.app.api;

public class ApiObject {

    public String issue;
    public String result;

    public ApiObject(String issue)
    {
        super();
        this.issue = issue;
    }

    public ApiObject() {super(); }

    public String getIssue() { return issue;}

    public void setResult(String result) { this.result = result; }
}
