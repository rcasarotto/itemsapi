package com.meli.itaccelerator.itemsapi.service;

import com.google.gson.Gson;
import com.meli.itaccelerator.itemsapi.model.Items;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

    /*
    * Esta clase se encarga de implementar la logica de servicio con Elasticsearch
    * @author: Rodolfo Casarotto
    * */

public class ItemsServiceElasticImp implements ItemsService{

    private Map<String, Object> itemsMap;

    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http")));



    public ItemsServiceElasticImp()
    {
        itemsMap = new HashMap<>();
    }

    /*
    * @param JsonObject recibe un objeto json y lo inserta dentro del indice Items como tipo item
    *
    * */
    @Override
    public void addItem(Items item, String id) {

        IndexRequest request = new IndexRequest("items","item",id);

        String json;
        json = new Gson().toJson(item);
        request.source(json,XContentType.JSON);
        try {
           IndexResponse response = client.index(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Map<String, Object> getItem(String id) {
        /*
        * @path /items/id
        * @param id identificador del item.
        * @return devuelve el objeto del item.
        * */
        GetRequest request = new GetRequest("items","item",id);
        try {
            GetResponse getResponse = client.get(request);
            if(getResponse.isExists())
            {
                 itemsMap = getResponse.getSourceAsMap();
                 return itemsMap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsMap;
    }

    @Override
    public Items editItem(Items item, String id) throws ItemsException {

        UpdateRequest request = new UpdateRequest("items", "item", id);
        String json;
        json = new Gson().toJson(item);
        request.doc(json,XContentType.JSON);
        try {
            client.update(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return item;
    }
    @Override
    public void deleteItem(String id) {
        DeleteRequest request = new DeleteRequest("items","item",id);
        try {
            client.delete(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
