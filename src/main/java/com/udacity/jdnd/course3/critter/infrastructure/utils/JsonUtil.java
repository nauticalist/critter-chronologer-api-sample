package com.udacity.jdnd.course3.critter.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    /**
     * Utility method to convert object to json string
     * @param object
     * @return json
     */
    public static String convertObjectToJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch(JsonProcessingException e) {
            // Ignore
        }
        return null;
    }
}
