package stores;

import products.Soap;

import java.util.Set;


public interface SoapStore extends GeneralStore {

    Set<Soap> soapProducts();

}
