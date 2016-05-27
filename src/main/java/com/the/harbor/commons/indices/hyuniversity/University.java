package com.the.harbor.commons.indices.hyuniversity;

public class University {

    private String universityId;

    private String universityName;

    private String countryCode;

    private UniversityNameSuggest universityNameSuggest;

    public University() {

    }

    public University(String universityId, String universityName, String countryCode) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.countryCode = countryCode;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public UniversityNameSuggest getUniversityNameSuggest() {
        return universityNameSuggest;
    }

    public void setUniversityNameSuggest(UniversityNameSuggest universityNameSuggest) {
        this.universityNameSuggest = universityNameSuggest;
    }

}
