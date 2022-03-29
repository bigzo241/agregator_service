package ujkz.tp.AgregateurService;

public class Stock {
    private final int id;

    private final int produitId;

    private final int quantite;


    public Stock() {
        this.id = -1;
        this.produitId = -1;
        this.quantite = 0;
    }

    public Stock(int id, int produitId, int quantite) {
        this.id = id;
        this.produitId = produitId;
        this.quantite = quantite;
    }

    public int getId() {
        return this.id;
    }

    public int getProduitId() {
        return this.produitId;
    }

    public int getQuantite() {
        return this.quantite;
    }
}
