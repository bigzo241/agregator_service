package ujkz.tp.AgregateurService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AgregateurServiceApplication {

	public static void main(String[] args) {

        SpringApplication.run(AgregateurServiceApplication.class, args);

        ClientsServices psci = new ClientsServices();
        /*
        Produit p = new Produit(20, "Ordinateur", "Laptop derniere generation", 100000);
        Stock stock = new Stock(1, 20, 10);
        int r = psci.createProduitAvecStock(p, stock);
        System.out.print(r);

        System.out.println("\n");
        System.out.println("Recuperation des produits et des stocks");
        List<String> listProduitStocks = psci.getProduitsAndStocks();
        System.out.println(listProduitStocks);
        */

//            Stock stock = new Stock(4, 1, 10);
//        psci.setStockOfProduct(stock);
        List<ProduitStock> produitStockList = psci.getProduitsAndStocks();
        System.out.println("\n \nAffichage des produits et de leur stock \n \n");
        System.out.println("Taille de la list : " + produitStockList.size());

        for (ProduitStock ps: produitStockList) {
            System.out.println("*************************************");
            System.out.println("Numéro du produit : " + ps.getProduit().getId());
            System.out.println("Produit : " + ps.getProduit().getDesignation());
            System.out.println("Description : " + ps.getProduit().getDescription());
            System.out.println("Quantité : " + ps.getQuantite());
            System.out.println("************************************* \n");
        }
	}

}
