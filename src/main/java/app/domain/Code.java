package app.domain;

//

public class Code {

    private String name;
    private String country;
    private String code;

    public Code() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" + "'name':'" + name + "',"+'\n' +
                "'country':'" + country + "',"+'\n' +
                "'code':'" + code + "'"+'\n' +
                '}';
    }
}