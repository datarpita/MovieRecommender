
package com.example.movieRecommender.json.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Results"
})
public class Output {

    @JsonProperty("Results")
    private Results results;
    /*@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();*/

    @JsonProperty("Results")
    public Results getResults() {
        return results;
    }

    @JsonProperty("Results")
    public void setResults(Results results) {
        this.results = results;
    }

    public Output withResults(Results results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /*@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Output withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }*/

    /*@Override
    public int hashCode() {
        return new HashCodeBuilder().append(results).append(additionalProperties).toHashCode();
    }*/
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(results).toHashCode();
    }

    /*@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Output) == false) {
            return false;
        }
        Output rhs = ((Output) other);
        return new EqualsBuilder().append(results, rhs.results).append(additionalProperties, rhs.additionalProperties).isEquals();
    }*/
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Output) == false) {
            return false;
        }
        Output rhs = ((Output) other);
        return new EqualsBuilder().append(results, rhs.results).isEquals();
    }

}
