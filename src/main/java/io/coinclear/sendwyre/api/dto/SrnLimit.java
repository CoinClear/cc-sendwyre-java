package io.coinclear.sendwyre.api.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "bucketKey",
        "amount",
        "timePeriod",
        "owner",
        "createdAt",
        "updatedAt",
        "consumed"
})
public class SrnLimit {

    @JsonProperty("id")
    public String id;
    @JsonProperty("bucketKey")
    public String bucketKey;
    @JsonProperty("amount")
    public double amount;
    @JsonProperty("timePeriod")
    public long timePeriod;
    @JsonProperty("owner")
    public String owner;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("updatedAt")
    public long updatedAt;
    @JsonProperty("consumed")
    public long consumed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}