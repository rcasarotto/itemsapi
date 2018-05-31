package com.meli.itaccelerator.itemsapi.service;

import com.meli.itaccelerator.itemsapi.model.Items;

import java.util.Collection;
import java.util.Map;

public interface ItemsService {
    public void addItem(Items item, String id);

    public Map<String, Object> getItem(String id);

    public Items editItem(Items item, String id)
        throws ItemsException;
    public void deleteItem(String id);

}
