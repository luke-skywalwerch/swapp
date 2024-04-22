package com.daw.swapp.model;

public class CharacterDetailsResponse {
    private String message;
    private CharacterDetailsResult result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CharacterDetailsResult getResult() {
        return result;
    }

    public CharacterDetails getProperties() {
        return result.properties;
    }

    public void setResult(CharacterDetailsResult result) {
        this.result = result;
    }

    public static class CharacterDetailsResult {
        private CharacterDetails properties;

        public CharacterDetails getProperties() {
            return properties;
        }

        public void setProperties(CharacterDetails properties) {
            this.properties = properties;
        }
    }
}
