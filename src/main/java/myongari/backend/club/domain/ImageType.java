package myongari.backend.club.domain;

public enum ImageType {

    PNG, JPG;

    public String getLowerCase() {
        return this.name().toLowerCase();
    }
}
