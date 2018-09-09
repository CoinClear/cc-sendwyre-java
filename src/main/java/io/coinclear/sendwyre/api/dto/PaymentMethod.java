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
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "owner",
        "createdAt",
        "name",
        "defaultCurrency",
        "status",
        "statusMessage",
        "waitingPrompts",
        "linkType",
        "beneficiaryType",
        "supportsDeposit",
        "nameOnMethod",
        "last4Digits",
        "brand",
        "expirationDisplay",
        "countryCode",
        "nickname",
        "rejectionMessage",
        "disabled",
        "supportsPayment",
        "chargeableCurrencies",
        "depositableCurrencies",
        "chargeFeeSchedule",
        "depositFeeSchedule",
        "minCharge",
        "maxCharge",
        "minDeposit",
        "maxDeposit",
        "documents",
        "srn"
})
public class PaymentMethod {

    @JsonProperty("id")
    public String id;
    @JsonProperty("owner")
    public String owner;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("name")
    public String name;
    @JsonProperty("defaultCurrency")
    public String defaultCurrency;
    @JsonProperty("status")
    public String status;
    @JsonProperty("statusMessage")
    public Object statusMessage;
    @JsonProperty("waitingPrompts")
    public List<WaitingPrompt> waitingPrompts = null;
    @JsonProperty("linkType")
    public String linkType;
    @JsonProperty("beneficiaryType")
    public String beneficiaryType;
    @JsonProperty("supportsDeposit")
    public boolean supportsDeposit;
    @JsonProperty("nameOnMethod")
    public Object nameOnMethod;
    @JsonProperty("last4Digits")
    public Object last4Digits;
    @JsonProperty("brand")
    public Object brand;
    @JsonProperty("expirationDisplay")
    public Object expirationDisplay;
    @JsonProperty("countryCode")
    public Object countryCode;
    @JsonProperty("nickname")
    public Object nickname;
    @JsonProperty("rejectionMessage")
    public Object rejectionMessage;
    @JsonProperty("disabled")
    public boolean disabled;
    @JsonProperty("supportsPayment")
    public boolean supportsPayment;
    @JsonProperty("chargeableCurrencies")
    public List<String> chargeableCurrencies = null;
    @JsonProperty("depositableCurrencies")
    public List<String> depositableCurrencies = null;
    @JsonProperty("chargeFeeSchedule")
    public Object chargeFeeSchedule;
    @JsonProperty("depositFeeSchedule")
    public Object depositFeeSchedule;
    @JsonProperty("minCharge")
    public Object minCharge;
    @JsonProperty("maxCharge")
    public Object maxCharge;
    @JsonProperty("minDeposit")
    public Object minDeposit;
    @JsonProperty("maxDeposit")
    public Object maxDeposit;
    @JsonProperty("documents")
    public List<Object> documents = null;
    @JsonProperty("srn")
    public String srn;
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