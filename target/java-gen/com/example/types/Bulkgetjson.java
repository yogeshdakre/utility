
package com.example.types;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * An Address following the convention of http://microformats.org/wiki/hcard
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "post-office-box",
    "extended-address",
    "street-address",
    "locality",
    "region",
    "postal-code",
    "country-name"
})
public class Bulkgetjson {

    @JsonProperty("post-office-box")
    private String post_office_box;
    @JsonProperty("extended-address")
    private String extended_address;
    @JsonProperty("street-address")
    private String street_address;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("locality")
    private String locality;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    private String region;
    @JsonProperty("postal-code")
    private String postal_code;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("country-name")
    private String country_name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("post-office-box")
    public String getPost_office_box() {
        return post_office_box;
    }

    @JsonProperty("post-office-box")
    public void setPost_office_box(String post_office_box) {
        this.post_office_box = post_office_box;
    }

    @JsonProperty("extended-address")
    public String getExtended_address() {
        return extended_address;
    }

    @JsonProperty("extended-address")
    public void setExtended_address(String extended_address) {
        this.extended_address = extended_address;
    }

    @JsonProperty("street-address")
    public String getStreet_address() {
        return street_address;
    }

    @JsonProperty("street-address")
    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("locality")
    public String getLocality() {
        return locality;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("locality")
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("postal-code")
    public String getPostal_code() {
        return postal_code;
    }

    @JsonProperty("postal-code")
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("country-name")
    public String getCountry_name() {
        return country_name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("country-name")
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
