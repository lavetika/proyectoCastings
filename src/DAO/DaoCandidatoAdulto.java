/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import objetoNegocio.AgenteCasting;
import objetoNegocio.CandidatoAdulto;
import objetoNegocio.CandidatoNiño;

/**
 *
 * @author laura
 */
public class DaoCandidatoAdulto extends DaoBase<CandidatoAdulto>{
    /**
     * Método implementado de la clase BaseRepository para guardar un CandidatoAdulto
     * @param adulto  
     * @return   
     */
    @Override
    public boolean guardar(CandidatoAdulto adulto) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin(); 
        if(adulto!=null){
           entityManager.persist(adulto);
        }else{
            return false;
        }
        
        entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Método implementado de la clase BaseRepository para eliminar un CandidatoAdulto
     * por id
     * @param id 
     * @return  
     */
    @Override
    public boolean eliminar(long id) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        
        CandidatoAdulto adulto = entityManager.find(CandidatoAdulto.class, id);
        if(adulto != null){
            entityManager.remove(adulto);
        }else{
            return false;
        }        
        entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Método implementado de la clase BaseRepository para actualizar un CandidatoAdulto
     * @param adultoActualizado 
     * @return  
     */
    @Override
    public boolean actualizar(CandidatoAdulto adultoActualizado) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        CandidatoAdulto adulto = entityManager.find(CandidatoAdulto.class, adultoActualizado.getId());
        if(adulto != null){
            adulto.setNombre(adultoActualizado.getNombre());
            adulto.setCurp(adultoActualizado.getCurp());
            adulto.setEdad(adultoActualizado.getEdad());
            adulto.setRepresentante(adultoActualizado.getRepresentante());
            adulto.setRfc(adultoActualizado.getRfc());
            entityManager.merge(adulto);
        }else{
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Método implementado de la clase BaseRepository para buscar un CandidatoAdulto
     * en especifico por id
     * @param id
     * @return 
     */
    @Override
    public CandidatoAdulto buscarPorId(long id) {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        CandidatoAdulto adulto = entityManager.find(CandidatoAdulto.class, id);
        if(adulto != null){
           entityManager.getTransaction().commit();
           return adulto;
        }
        entityManager.getTransaction().commit();
        return null;
    }

    /**
     * Método implementado de la clase BaseRepository para buscar todas los CandidatoAdultos
     * y que regresa un ArrayList con ellos
     * @return 
     */
    @Override
    public ArrayList<CandidatoAdulto> buscarTodas() {
        EntityManager entityManager = this.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
        criteria.select(criteria.from(CandidatoAdulto.class));
        Query query = entityManager.createQuery(criteria);
        List<CandidatoAdulto> adultos = query.getResultList();
        entityManager.getTransaction().commit();
        return new ArrayList<>(adultos);
    }
}
