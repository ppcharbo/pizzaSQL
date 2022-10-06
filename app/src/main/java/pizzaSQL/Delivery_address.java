package pizzaSQL;

public class Delivery_address {
    private String street;
    private String city;
    private String country;
    private String zipcode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    private Delivery_address(String street, String city, String country, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

	public static Delivery_address createRandomAddress() {
		
		return createDelivery_address("Av Nouvelle", "Brx", "BEl", "1040");
	}

	public static Delivery_address createDelivery_address(String street, String city, String country, String zipcode) {
		return new Delivery_address(street, city, country, zipcode);
	}

	@Override
	public String toString() {
		return "Delivery_address [street=" + street + ", city=" + city + ", country=" + country + ", zipcode=" + zipcode
				+ "]";
	}
	
}
