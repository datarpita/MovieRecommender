
package com.example.movieRecommender.json.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Inputs",
    "GlobalParameters"
})
public class Input {

    @JsonProperty("Inputs")
    private Inputs inputs;
    @JsonProperty("GlobalParameters")
    private GlobalParameters globalParameters;
   /* @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();*/

    @JsonProperty("Inputs")
    public Inputs getInputs() {
        return inputs;
    }

    @JsonProperty("Inputs")
    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }

    public Input withInputs(Inputs inputs) {
        this.inputs = inputs;
        return this;
    }

    @JsonProperty("GlobalParameters")
    public GlobalParameters getGlobalParameters() {
        return globalParameters;
    }

    @JsonProperty("GlobalParameters")
    public void setGlobalParameters(GlobalParameters globalParameters) {
        this.globalParameters = globalParameters;
    }

    public Input withGlobalParameters(GlobalParameters globalParameters) {
        this.globalParameters = globalParameters;
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

    public Input withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }*/

    /*@Override
    public int hashCode() {
        return new HashCodeBuilder().append(inputs).append(globalParameters).append(additionalProperties).toHashCode();
    }*/
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(inputs).append(globalParameters).toHashCode();
    }

    /*@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Input) == false) {
            return false;
        }
        Input rhs = ((Input) other);
        return new EqualsBuilder().append(inputs, rhs.inputs).append(globalParameters, rhs.globalParameters).append(additionalProperties, rhs.additionalProperties).isEquals();
    }*/
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Input) == false) {
            return false;
        }
        Input rhs = ((Input) other);
        return new EqualsBuilder().append(inputs, rhs.inputs).append(globalParameters, rhs.globalParameters).isEquals();
    }

}
