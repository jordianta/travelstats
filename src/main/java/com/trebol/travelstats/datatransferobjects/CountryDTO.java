package com.trebol.travelstats.datatransferobjects;

public class CountryDTO {

    private final Integer id;
    private final String name;
    private final Integer continentId;
    private final String isoCode;

    public CountryDTO(final Integer id, final String name, final Integer continentId, final String isoCode) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.isoCode = isoCode;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public String getIsoCode() {
        return isoCode;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continentId=" + continentId +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CountryDTO that = (CountryDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (continentId != null ? !continentId.equals(that.continentId) : that.continentId != null) return false;
        return isoCode != null ? isoCode.equals(that.isoCode) : that.isoCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continentId != null ? continentId.hashCode() : 0);
        result = 31 * result + (isoCode != null ? isoCode.hashCode() : 0);
        return result;
    }
}
