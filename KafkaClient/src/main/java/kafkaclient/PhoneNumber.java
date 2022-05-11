package kafkaclient;

public class PhoneNumber {
    private String areaCode;
    private String countryCode;
    private String prefix;
    private String number;

    public PhoneNumber(String areaCode, String countryCode, String prefix, String number) {
        this.areaCode = areaCode;
        this.countryCode = countryCode;
        this.prefix = prefix;
        this.number = number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode='" + areaCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", prefix='" + prefix + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
