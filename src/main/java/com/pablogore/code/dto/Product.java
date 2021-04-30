package com.pablogore.code.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;


@JsonSerialize(as = ImmutableProduct.class)
@JsonDeserialize(as = ImmutableProduct.class)
@Value.Immutable
public interface Product {
    @Nullable
    @JsonProperty("name")
    public abstract String getName();
    @Nullable
    @JsonProperty("description")
    public abstract String getDescription();
    @Nullable
    @JsonProperty("weight")
    public abstract Double getWeight();
    @Nullable
    @JsonProperty("price")
    public abstract Double getPrice();
    @Nullable
    @JsonProperty("country")
    public abstract String getCountry();
}
