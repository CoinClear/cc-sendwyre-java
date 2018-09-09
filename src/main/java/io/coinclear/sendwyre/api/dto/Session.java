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
        "seenAt",
        "expiresAt",
        "deviceType",
        "apiSession",
        "ipAddress",
        "language",
        "mfaRequired",
        "mfaAuthedAt",
        "mfaTokenDispatchedAt",
        "ipLock",
        "lockedIpAddresses",
        "destSrnWhitelist",
        "mfaAuthorized",
        "city",
        "country"
})
public class Session {

    @JsonProperty("id")
    public String id;
    @JsonProperty("owner")
    public String owner;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("seenAt")
    public long seenAt;
    @JsonProperty("expiresAt")
    public Object expiresAt;
    @JsonProperty("deviceType")
    public Object deviceType;
    @JsonProperty("apiSession")
    public boolean apiSession;
    @JsonProperty("ipAddress")
    public String ipAddress;
    @JsonProperty("language")
    public String language;
    @JsonProperty("mfaRequired")
    public boolean mfaRequired;
    @JsonProperty("mfaAuthedAt")
    public Object mfaAuthedAt;
    @JsonProperty("mfaTokenDispatchedAt")
    public Object mfaTokenDispatchedAt;
    @JsonProperty("ipLock")
    public boolean ipLock;
    @JsonProperty("lockedIpAddresses")
    public List<Object> lockedIpAddresses = null;
    @JsonProperty("destSrnWhitelist")
    public List<Object> destSrnWhitelist = null;
    @JsonProperty("mfaAuthorized")
    public boolean mfaAuthorized;
    @JsonProperty("city")
    public Object city;
    @JsonProperty("country")
    public Object country;
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