package ujkz.tp.AgregateurService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ClientsServices {

    private static final String PRODUIT_SERVICE_URL = "http://127.0.0.1:8001";
    private static final String STOCK_SERVICE_URL = "http://127.0.0.1:8002";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();



    public int createProduitAvecStock(Produit produit, Stock stock) {
        // Enregistrement d'un Produit
        RequestBody body = RequestBody.create(new Gson().toJson(produit), JSON);
        Request request = new Request.Builder().url(ClientsServices.PRODUIT_SERVICE_URL + "/produit").post(body)
                .build();
        try {
            Response response = this.client.newCall(request).execute();
            String out = response.body().string();

            if (out != null) {
                System.out.println("Le produit a été enregistré correctement");

                RequestBody bodyStock = RequestBody.create(new Gson().toJson(stock), JSON);
                Request requestStock = new Request.Builder().url(ClientsServices.STOCK_SERVICE_URL + "/stock").post(bodyStock)
                        .build();
                response = this.client.newCall(requestStock).execute();
                if (response.body().string() != null) {
                    System.out.println("Enregistrement du stock réussi");
                } else {
                    System.out.println("Stock non enregistré");
                }

            } else {
                System.out.println("Produit non enregistré");
            }
            // Si Enregistrement du Produit est OK
            // Enregistrer le Stock lie a ce Produit

            // TODO
            response.close();
            System.out.println(out);
            return Integer.parseInt(out);

        } catch (Exception e) {
            return -1;
        }
    }

    public List<ProduitStock> getProduitsAndStocks() {
        Request requestProduit = new Request.Builder().url(ClientsServices.PRODUIT_SERVICE_URL + "/produit").get()
                .build();
        Gson gson = new Gson();
        ArrayList<ProduitStock> listProduitStock = new ArrayList<ProduitStock>();
        try {
            Response response1 = this.client.newCall(requestProduit).execute();
            ArrayList<Produit> listProduits = gson.fromJson(response1.body().string(), new TypeToken<ArrayList<Produit>>(){}.getType());
            response1.close();

            if (listProduits.isEmpty()){
                System.out.println("Il n'y a aucun produit");
            } else {
                for (Produit p : listProduits) {
                    ProduitStock produitStock = new ProduitStock();
                    produitStock.setProduit(p);

                    Request requestStock = new Request.Builder().url(ClientsServices.STOCK_SERVICE_URL + "/stock/" + p.getId()).get()
                            .build();
                    Response response2 = this.client.newCall(requestStock).execute();

                    Integer quantite = gson.fromJson(response2.body().string(), Integer.class);

                    if (quantite == null) {
                        produitStock.setQuantite(0);
                        listProduitStock.add(produitStock);
                    } else {
                        produitStock.setQuantite(quantite);
                        listProduitStock.add(produitStock);
                    }

                    System.out.println("Ajout du ProduitStock dans la liste");
                    response2.close();
                }
            }

            return listProduitStock;
        } catch (Exception e) {
            System.out.println("Il y a un problème");
            return null;
        }

    }

    public void setStockOfProduct(Stock stock) {
        Request requestProduit = new Request.Builder().url(ClientsServices.PRODUIT_SERVICE_URL + "/produit/" + stock.getProduitId()).get()
                .build();
        try {
            Response response = this.client.newCall(requestProduit).execute();
            String contentOfResponse = response.body().string();
            if (contentOfResponse.isEmpty()){
                System.out.println("Le produit d'ID : " + stock.getProduitId() + " n'existe pas");
            } else {
                RequestBody bodyStock = RequestBody.create(new Gson().toJson(stock), JSON);
                Request requestStock = new Request.Builder().url(ClientsServices.STOCK_SERVICE_URL + "/stock").post(bodyStock)
                        .build();
                response = this.client.newCall(requestStock).execute();
                if (response.body().string() != null) {
                    System.out.println("Enregistrement du stock réussi");
                } else {
                    System.out.println("Stock non enregistré");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public
}
