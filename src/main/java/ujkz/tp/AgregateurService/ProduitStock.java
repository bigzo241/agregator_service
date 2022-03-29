package ujkz.tp.AgregateurService;

public class ProduitStock {
    private Produit produit;
    private int quantite;

    public ProduitStock(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public ProduitStock() {}

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
