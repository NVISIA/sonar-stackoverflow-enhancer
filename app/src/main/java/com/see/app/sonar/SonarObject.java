
package com.see.app.sonar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "p",
    "ps",
    "paging",
    "issues",
    "components"
})
public class SonarObject {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("p")
    private Integer p;
    @JsonProperty("ps")
    private Integer ps;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("issues")
    private List<Issue> issues = null;
    @JsonProperty("components")
    private List<Component> components = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("p")
    public Integer getP() {
        return p;
    }

    @JsonProperty("p")
    public void setP(Integer p) {
        this.p = p;
    }

    @JsonProperty("ps")
    public Integer getPs() {
        return ps;
    }

    @JsonProperty("ps")
    public void setPs(Integer ps) {
        this.ps = ps;
    }

    @JsonProperty("paging")
    public Paging getPaging() {
        return paging;
    }

    @JsonProperty("paging")
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @JsonProperty("issues")
    public List<Issue> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @JsonProperty("components")
    public List<Component> getComponents() {
        return components;
    }

    @JsonProperty("components")
    public void setComponents(List<Component> components) {
        this.components = components;
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
        return new ToStringBuilder(this).append("total", total).append("p", p).append("ps", ps).append("paging", paging).append("issues", issues).append("components", components).append("additionalProperties", additionalProperties).toString();
    }

}
