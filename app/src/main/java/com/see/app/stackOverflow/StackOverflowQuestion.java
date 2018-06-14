package com.see.app.stackOverflow;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tags",
        "owner",
        "is_answered",
        "view_count",
        "protected_date",
        "accepted_answer_id",
        "answer_count",
        "score",
        "last_activity_date",
        "creation_date",
        "last_edit_date",
        "question_id",
        "link",
        "title"
})
public class StackOverflowQuestion {

    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("is_answered")
    private Boolean isAnswered;
    @JsonProperty("view_count")
    private Integer viewCount;
    @JsonProperty("protected_date")
    private Integer protectedDate;
    @JsonProperty("accepted_answer_id")
    private Integer acceptedAnswerId;
    @JsonProperty("answer_count")
    private Integer answerCount;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("last_activity_date")
    private Integer lastActivityDate;
    @JsonProperty("creation_date")
    private Integer creationDate;
    @JsonProperty("last_edit_date")
    private Integer lastEditDate;
    @JsonProperty("question_id")
    private Integer questionId;
    @JsonProperty("link")
    private String link;
    @JsonProperty("title")
    private String title;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("is_answered")
    public Boolean getIsAnswered() {
        return isAnswered;
    }

    @JsonProperty("is_answered")
    public void setIsAnswered(Boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    @JsonProperty("view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    @JsonProperty("view_count")
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("protected_date")
    public Integer getProtectedDate() {
        return protectedDate;
    }

    @JsonProperty("protected_date")
    public void setProtectedDate(Integer protectedDate) {
        this.protectedDate = protectedDate;
    }

    @JsonProperty("accepted_answer_id")
    public Integer getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    @JsonProperty("accepted_answer_id")
    public void setAcceptedAnswerId(Integer acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    @JsonProperty("answer_count")
    public Integer getAnswerCount() {
        return answerCount;
    }

    @JsonProperty("answer_count")
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("last_activity_date")
    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    @JsonProperty("last_activity_date")
    public void setLastActivityDate(Integer lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @JsonProperty("creation_date")
    public Integer getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("last_edit_date")
    public Integer getLastEditDate() {
        return lastEditDate;
    }

    @JsonProperty("last_edit_date")
    public void setLastEditDate(Integer lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @JsonProperty("question_id")
    public Integer getQuestionId() {
        return questionId;
    }

    @JsonProperty("question_id")
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tags", tags).append("owner", owner).append("isAnswered", isAnswered).append("viewCount", viewCount).append("protectedDate", protectedDate).append("acceptedAnswerId", acceptedAnswerId).append("answerCount", answerCount).append("score", score).append("lastActivityDate", lastActivityDate).append("creationDate", creationDate).append("lastEditDate", lastEditDate).append("questionId", questionId).append("link", link).append("title", title).append("additionalProperties", additionalProperties).toString();
    }

}
