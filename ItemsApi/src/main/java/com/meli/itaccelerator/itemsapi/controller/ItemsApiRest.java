package com.meli.itaccelerator.itemsapi.controller;
import com.google.gson.Gson;
import com.meli.itaccelerator.itemsapi.model.Items;
import com.meli.itaccelerator.itemsapi.service.ItemsService;
import com.meli.itaccelerator.itemsapi.service.ItemsServiceElasticImp;
import com.meli.itaccelerator.itemsapi.service.StandardResponse;
import com.meli.itaccelerator.itemsapi.service.StatusResponse;
import static spark.Spark.*;

public class ItemsApiRest {

    public static void main(String[] args) {


        port(8080);

        final ItemsService itemsService = new ItemsServiceElasticImp();

        get("/items/:id",((request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(itemsService.getItem(request.params(":id")))));
        }));

        post("/items/:id", (request, response) -> {
            response.type("application/json");
            Items items = new Gson().fromJson(request.body(),Items.class);
            itemsService.addItem(items, request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        put("/items/:id",(request, response) -> {
            response.type("application/json");
            Items toEdit = new Gson().fromJson(request.body(),Items.class);
            Items editItems = itemsService.editItem(toEdit, request.params(":id"));
            if(editItems != null)
            {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editItems)));
            }else
            {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Item no encontrado o error al editar")));
            }
        });

        delete("/items/:id",((request, response) -> {
            response.type("application/json");
            itemsService.deleteItem(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Item Borrado"));

        }));

    }
}
