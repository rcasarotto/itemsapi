API de Items

Verbo HTTP: GET
PATH: /items/id
RETURN: retorna un objeto json del item id.
Ejemplo:
{
    "_index": "items",
    "_type": "item",
    "_id": "14",
    "_version": 1,
    "found": true,
    "_source": {
    "title": "Campera",
    "category_id": "MLA123",
    "price": 800,
    "currency_id": "$ARS",
    "available_quantity": 50,
    "buying_mode": "para vender",
    "listing_type_id": "bronze",
    "condition": "new",
    "description": "producto de prueba",
    "video_id": "YOUTUBE ID",
    "warranty": "1 año",
    "pictures": [
    {
            "source": "https://i0.wp.com/caciqueoutdoors.com/wp-content/uploads/2017/02/Campera-Nevada-ma-webnva.jpg?f  it=1000%2C1000&ssl=1"
            },
            {
            "source": "https://http2.mlstatic.com/S_932405-MLA25001446063_082016-O.jpg"
            }
            ]
  }
}
