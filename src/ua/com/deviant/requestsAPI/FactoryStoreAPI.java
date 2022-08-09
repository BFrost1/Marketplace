package ua.com.deviant.requestsAPI;

public class FactoryStoreAPI {

	public static StoreAPI creatConnetcStore(String nameCompany) {
		StoreAPI storeAPI = null;
		switch (nameCompany) {
		case "store_phone":
			storeAPI = new StorePhoneAPI();
			break;
		case "store_tablet":
			storeAPI = new StoreTabletAPI();
			break;
		}
		return storeAPI;
	}
}
