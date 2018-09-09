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
        "owner",
        "callbackUrl",
        "depositAddresses",
        "totalBalances",
        "availableBalances",
        "verificationData",
        "balances",
        "srn",
        "createdAt",
        "notes",
        "name",
        "id"
})
public class Wallet {

    @JsonProperty("owner")
    public String owner;
    @JsonProperty("callbackUrl")
    public String callbackUrl;
    @JsonProperty("depositAddresses")
    public DepositAddresses depositAddresses;
    @JsonProperty("totalBalances")
    public TotalBalances totalBalances;
    @JsonProperty("availableBalances")
    public AvailableBalances availableBalances;
    @JsonProperty("verificationData")
    public String verificationData;
    @JsonProperty("balances")
    public AvailableBalances balances;
    @JsonProperty("srn")
    public String srn;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("notes")
    public String notes;
    @JsonProperty("name")
    public String name;
    @JsonProperty("id")
    public String id;
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