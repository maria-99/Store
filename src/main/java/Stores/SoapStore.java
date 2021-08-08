package Stores;

import Products.Soap;

import java.util.Set;


public interface SoapStore extends GeneralStore {

    Set<Soap> soapProducts();

}
