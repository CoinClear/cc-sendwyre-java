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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "firstName",
        "lastName",
        "language",
        "address",
        "businessAccount",
        "taxId",
        "doingBusinessAs",
        "website",
        "ssn",
        "dateOfBirth",
        "notifyEmail",
        "notifyCellphone",
        "notifyApnsDevice",
        "onboardingDashboardCompleted",
        "displayCurrency",
        "cpfNumber",
        "type",
        "vertical",
        "ethereumVerificationAddress",
        "country",
        "name"
})
public class Profile {

    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("language")
    public String language;
    @JsonProperty("address")
    public Address address;
    @JsonProperty("businessAccount")
    public boolean businessAccount;
    @JsonProperty("taxId")
    public Object taxId;
    @JsonProperty("doingBusinessAs")
    public Object doingBusinessAs;
    @JsonProperty("website")
    public Object website;
    @JsonProperty("ssn")
    public Object ssn;
    @JsonProperty("dateOfBirth")
    public Object dateOfBirth;
    @JsonProperty("notifyEmail")
    public boolean notifyEmail;
    @JsonProperty("notifyCellphone")
    public boolean notifyCellphone;
    @JsonProperty("notifyApnsDevice")
    public Object notifyApnsDevice;
    @JsonProperty("onboardingDashboardCompleted")
    public boolean onboardingDashboardCompleted;
    @JsonProperty("displayCurrency")
    public String displayCurrency;
    @JsonProperty("cpfNumber")
    public Object cpfNumber;
    @JsonProperty("type")
    public String type;
    @JsonProperty("vertical")
    public String vertical;
    @JsonProperty("ethereumVerificationAddress")
    public Object ethereumVerificationAddress;
    @JsonProperty("country")
    public String country;
    @JsonProperty("name")
    public Object name;
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