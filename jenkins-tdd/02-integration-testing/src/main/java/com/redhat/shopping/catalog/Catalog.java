package com.redhat.shopping.catalog;

public interface Catalog {

    Product ofId(int id) throws ProductNotFoundInCatalogException;
}
