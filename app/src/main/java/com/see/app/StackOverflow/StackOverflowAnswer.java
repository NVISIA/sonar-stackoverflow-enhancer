package com.see.app.StackOverflow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "owner",
            "score",
            "last_edit_date",
            "last_activity_date",
            "creation_date",
            "post_type",
            "post_id",
            "link",
            "body"
    })

public class StackOverflowAnswer implements Serializable {

        @JsonProperty("owner")
        private Owner owner;
        @JsonProperty("score")
        private Integer score;
        @JsonProperty("last_edit_date")
        private Integer lastEditDate;
        @JsonProperty("last_activity_date")
        private Integer lastActivityDate;
        @JsonProperty("creation_date")
        private Integer creationDate;
        @JsonProperty("post_type")
        private String postType;
        @JsonProperty("post_id")
        private Integer postId;
        @JsonProperty("link")
        private String link;
        @JsonProperty("body")
        private String body;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("owner")
        public Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public StackOverflowAnswer withOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        @JsonProperty("score")
        public Integer getScore() {
            return score;
        }

        @JsonProperty("score")
        public void setScore(Integer score) {
            this.score = score;
        }

        public StackOverflowAnswer withScore(Integer score) {
            this.score = score;
            return this;
        }

        @JsonProperty("last_edit_date")
        public Integer getLastEditDate() {
            return lastEditDate;
        }

        @JsonProperty("last_edit_date")
        public void setLastEditDate(Integer lastEditDate) {
            this.lastEditDate = lastEditDate;
        }

        public StackOverflowAnswer withLastEditDate(Integer lastEditDate) {
            this.lastEditDate = lastEditDate;
            return this;
        }

        @JsonProperty("last_activity_date")
        public Integer getLastActivityDate() {
            return lastActivityDate;
        }

        @JsonProperty("last_activity_date")
        public void setLastActivityDate(Integer lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
        }

        public StackOverflowAnswer withLastActivityDate(Integer lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
            return this;
        }

        @JsonProperty("creation_date")
        public Integer getCreationDate() {
            return creationDate;
        }

        @JsonProperty("creation_date")
        public void setCreationDate(Integer creationDate) {
            this.creationDate = creationDate;
        }

        public StackOverflowAnswer withCreationDate(Integer creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        @JsonProperty("post_type")
        public String getPostType() {
            return postType;
        }

        @JsonProperty("post_type")
        public void setPostType(String postType) {
            this.postType = postType;
        }

        public StackOverflowAnswer withPostType(String postType) {
            this.postType = postType;
            return this;
        }

        @JsonProperty("post_id")
        public Integer getPostId() {
            return postId;
        }

        @JsonProperty("post_id")
        public void setPostId(Integer postId) {
            this.postId = postId;
        }

        public StackOverflowAnswer withPostId(Integer postId) {
            this.postId = postId;
            return this;
        }

        @JsonProperty("link")
        public String getLink() {
            return link;
        }

        @JsonProperty("link")
        public void setLink(String link) {
            this.link = link;
        }

        public StackOverflowAnswer withLink(String link) {
            this.link = link;
            return this;
        }

        @JsonProperty("body")
        public String getBody() {
            return body;
        }

        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
        }

        public StackOverflowAnswer withBody(String body) {
            this.body = body;
            return this;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public StackOverflowAnswer withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("owner", owner).append("score", score).append("lastEditDate", lastEditDate).append("lastActivityDate", lastActivityDate).append("creationDate", creationDate).append("postType", postType).append("postId", postId).append("link", link).append("body", body).append("additionalProperties", additionalProperties).toString();
        }

}
