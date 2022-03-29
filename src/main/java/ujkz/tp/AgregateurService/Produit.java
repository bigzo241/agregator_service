package ujkz.tp.AgregateurService;

public class Produit {

    private final int id;

    private final String designation;

    private final String description;

    private final int prix;


    public Produit() {
        this.id = -1;
        this.designation = "";
        this.description = "";
        this.prix = -1;
    }

    public Produit(int id, String designation, String description, int prix) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.prix = prix;
    }

    public int getId() {
        return this.id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrix() {
        return this.prix;
    }
}
