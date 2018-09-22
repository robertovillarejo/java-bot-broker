
package io.github.robertovillarejo.bot.dialogflow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "parameters",
    "lifespan"
})
public class Context {

    @JsonProperty("name")
    private String name;
    @JsonProperty("parameters")
    private Parameters_ parameters;
    @JsonProperty("lifespan")
    private Integer lifespan;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("parameters")
    public Parameters_ getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(Parameters_ parameters) {
        this.parameters = parameters;
    }

    @JsonProperty("lifespan")
    public Integer getLifespan() {
        return lifespan;
    }

    @JsonProperty("lifespan")
    public void setLifespan(Integer lifespan) {
        this.lifespan = lifespan;
    }

}
