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
        "createdAt",
        "updatedAt",
        "referralUrl",
        "apnsToken",
        "tangoCardCustomer",
        "status",
        "profile",
        "paymentMethods",
        "identities",
        "depositAddresses",
        "ledgers",
        "session",
        "documents",
        "srnLimits",
        "pusherChannel",
        "email",
        "cellphone",
        "srn",
        "verified",
        "loginAt",
        "lastLoginIp",
        "lastLoginLocation",
        "totalBalances",
        "availableBalances",
        "type"
})
public class AccountDetails {

    @JsonProperty("id")
    public String id;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("updatedAt")
    public long updatedAt;
    @JsonProperty("referralUrl")
    public Object referralUrl;
    @JsonProperty("apnsToken")
    public Object apnsToken;
    @JsonProperty("tangoCardCustomer")
    public boolean tangoCardCustomer;
    @JsonProperty("status")
    public String status;
    @JsonProperty("profile")
    public Profile profile;
    @JsonProperty("paymentMethods")
    public List<PaymentMethod> paymentMethods = null;
    @JsonProperty("identities")
    public List<Identity> identities = null;
    @JsonProperty("depositAddresses")
    public DepositAddresses depositAddresses;
    @JsonProperty("ledgers")
    public List<Ledger> ledgers = null;
    @JsonProperty("session")
    public Session session;
    @JsonProperty("documents")
    public List<Object> documents = null;
    @JsonProperty("srnLimits")
    public List<SrnLimit> srnLimits = null;
    @JsonProperty("pusherChannel")
    public String pusherChannel;
    @JsonProperty("email")
    public String email;
    @JsonProperty("cellphone")
    public String cellphone;
    @JsonProperty("srn")
    public String srn;
    @JsonProperty("verified")
    public boolean verified;
    @JsonProperty("loginAt")
    public long loginAt;
    @JsonProperty("lastLoginIp")
    public String lastLoginIp;
    @JsonProperty("lastLoginLocation")
    public Object lastLoginLocation;
    @JsonProperty("totalBalances")
    public TotalBalances totalBalances;
    @JsonProperty("availableBalances")
    public AvailableBalances availableBalances;
    @JsonProperty("type")
    public String type;
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