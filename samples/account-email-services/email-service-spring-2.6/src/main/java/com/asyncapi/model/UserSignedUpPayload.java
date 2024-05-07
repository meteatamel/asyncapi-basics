package com.asyncapi.model;


import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.870Z")
public class UserSignedUpPayload {
    
    private @Valid String displayName;
    
    private @Valid String email;
    

    

    /**
     * Name of the user
     */
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    

    /**
     * Email of the user
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSignedUpPayload userSignedUpPayload = (UserSignedUpPayload) o;
        return 
            Objects.equals(this.displayName, userSignedUpPayload.displayName) &&
            Objects.equals(this.email, userSignedUpPayload.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, email);
    }

    @Override
    public String toString() {
        return "class UserSignedUpPayload {\n" +
        
                "    displayName: " + toIndentedString(displayName) + "\n" +
                "    email: " + toIndentedString(email) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
           return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}