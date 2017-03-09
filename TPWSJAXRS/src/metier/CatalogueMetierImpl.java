package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.entities.Categorie;
import metier.entities.Produit;

public class CatalogueMetierImpl implements ICatalogueMetier {

	private Map<Long, Categorie> categories = new HashMap<Long, Categorie>();
	private Map<Long, Produit> produits = new HashMap<Long, Produit>();

	@Override
	public Categorie addCategorie(Categorie c) {
		categories.put(c.getIdCategorie(), c);
		return c;
	}

	@Override
	public Produit addProduit(Produit p) {
		p.setCategorie(getCategorie(p.getCategorie().getIdCategorie()));
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public List<Categorie> listCategories() {
		return new ArrayList<Categorie>(categories.values());
	}

	@Override
	public List<Produit> listProduit() {
		// TODO Auto-generated method stub
		return new ArrayList<Produit>(produits.values());
	}

	@Override
	public List<Produit> produitParCat(Long idCat) {
		List<Produit> prods = new ArrayList<Produit>();
		for (Produit p : produits.values())
			if (p.getCategorie().getIdCategorie().equals(idCat))
				prods.add(p);
		return prods;
	}

	@Override
	public List<Produit> produitsParMC(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
		for (Produit p : produits.values())
			if (p.getDesignation().contains(mc))
				prods.add(p);
		return prods;
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		categories.put(c.getIdCategorie(), c);
		return c;
	}

	@Override
	public Produit updateProduit(Produit p) {
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public boolean deleteProduit(Long idprod) {
		if (produits.get(idprod) != null) {
			produits.remove(idprod);
			return true;
		} else
			throw new RuntimeException("Produit introuvable");
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		return categories.get(idCat);
	}

	@Override
	public Produit getProduit(Long idProd) {
		return produits.get(idProd);
	}

	public void initialisercatalogue() {
		addCategorie(new Categorie(1L, "Ordinateurs", "ordinateurs.jpg"));
		addCategorie(new Categorie(2L, "Imprimantes", "imprimantes.jpg"));
		addCategorie(new Categorie(3L, "Téléviseurs", "televiseurs.jpg"));
		produits.put(1L, new Produit(1L, "HP Pavilion 17-f227nf PC Porta",
				6500, "ord1.jpg", getCategorie(1L)));
		produits.put(2L, new Produit(2L, "Asus PC Portable Reconditionné",
				4500, "ord2.jpg", getCategorie(1L)));
		produits.put(3L, new Produit(3L, "Lenovo Notebook G5070", 3500,
				"ord3.jpg", getCategorie(1L)));
		produits.put(4L, new Produit(4L, "HP Imprimante Laser Laserjet", 1500,
				"imp1.jpg", getCategorie(2L)));
		produits.put(5L, new Produit(5L, "Canon Pixma MG 2440", 1000,
				"imp2.jpg", getCategorie(2L)));
		produits.put(6L, new Produit(6L, "HP LJ Pro CP1025 Color", 2200,
				"imp3.jpg", getCategorie(2L)));
	}
}
