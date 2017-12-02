package com.trebol.travelstats.datatransferobjects;

public class CarrierDTO {

    private final Integer id;
    private final String name;
    private final String iataCode;

    public CarrierDTO(final Integer id, final String name, final String iataCode) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIataCode() {
        return iataCode;
    }

    @Override
    public String toString() {
        return "CarrierDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CarrierDTO that = (CarrierDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return iataCode != null ? iataCode.equals(that.iataCode) : that.iataCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iataCode != null ? iataCode.hashCode() : 0);
        return result;
    }
}
