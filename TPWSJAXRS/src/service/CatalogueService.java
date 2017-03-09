package service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

import metier.CatalogueMetierImpl;
import metier.entities.Categorie;
import metier.entities.Produit;

@Singleton
@Path("/catalogue")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class CatalogueService {
	private CatalogueMetierImpl metier;

	public CatalogueService() {
		metier = new CatalogueMetierImpl();
		metier.initialisercatalogue();
	}

	@GET
	@Path("/categories")
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Categorie> consulterCategoris() {
		return metier.listCategories();
	}

	@GET
	@Path("/categories/{idCat}/produits")
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitsParCatalogue(
			@PathParam(value = "idCat") Long idCat) {
		return metier.produitParCat(idCat);
	}

	@GET
	@Path("/produits")
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitsParMC(@QueryParam(value = "mc") String mc) {
		return metier.produitsParMC(mc);
	}

	@GET
	@Path("/allProduits")
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produits() {
		return metier.listProduit();
	}

	@GET
	@Path("/categories/{idCat}")
	// @Produces(MediaType.APPLICATION_JSON)
	public Categorie getCategorie(@PathParam(value = "idCat") Long idCat) {
		return metier.getCategorie(idCat);
	}

	@GET
	@Path("/produits/{idProd}")
	// @Produces(MediaType.APPLICATION_JSON)
	public Produit getProduit(@PathParam(value = "idProd") Long idProd) {
		return metier.getProduit(idProd);
	}

	@POST
	@Path("/categories")
	// @Consumes(MediaType.APPLICATION_JSON)
	public Categorie savecategorie(Categorie c) {
		return metier.addCategorie(c);
	}

	@DELETE
	@Path("/produits")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean deleteProduit(@FormParam(value = "idProd") Long idProd) {
		return metier.deleteProduit(idProd);
	}

	@PUT
	@Path("/produits")
	public Produit updateProduit(Produit p) {
		return metier.updateProduit(p);
	}

}
