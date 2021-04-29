package com.pablogore.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import java.util.UUID;

@JsonSerialize(as = ImmutableProductResponse.class)
@JsonDeserialize(as = ImmutableProductResponse.class)
@Value.Immutable
public abstract class ProductResponse extends Product{
    @JsonProperty("id")
    public abstract UUID getId();
}
