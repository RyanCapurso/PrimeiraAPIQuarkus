package org.acme.UserController;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entity.UserEntity;

import java.util.List;

@Transactional // A classe abre a conexão com o banco
@ApplicationScoped
@Path("/User")
public class User {

    @PersistenceContext // Adiciona as Persistencias de banco
    EntityManager userManager;

    @POST
    @Path("/Cadastro") // Caminho da Aplicação
    @Consumes(MediaType.APPLICATION_JSON) // Consome uma entrada Json
    @Produces(MediaType.APPLICATION_JSON) // Produz uma saida Json
    public UserEntity cadastrar(UserEntity user){

        userManager.persist(user); //Adiciona ao banco
        return user;
    }

    @GET
    @Path("/ListarUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> Listar(){
        CriteriaBuilder criteriaBuilder = userManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity>  criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        criteriaQuery.from(UserEntity.class);
        return userManager.createQuery(criteriaQuery).getResultList();
    }

}
