
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Especialidade;

import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class EspecialidadeDAO implements Serializable{

   private String mensagem = "";
   private EntityManager em;
   
   public EspecialidadeDAO(){
       em = EntityManagerUtil.getEntityManager();
   }
   
   public List<Especialidade> getLista() {
        return getEm().createQuery("from Especialidade order by descricao").getResultList();
    }

    public boolean salvar(Especialidade obj) {
        try {
            getEm().getTransaction().begin();
            if (obj.getId() == null) {
                getEm().persist(obj);
            } else {
                getEm().merge(obj);
            }
            getEm().getTransaction().commit();
            setMensagem("Objeto persistido com sucesso");
            return true;
        } catch (Exception e) {
            if (getEm().getTransaction().isActive() == false) {
                getEm().getTransaction().begin();
            }
            getEm().getTransaction().rollback();
            setMensagem("Erro ao persistir objeto: " + Util.getMensagemErro(e));
            return false;
        }
    }

    public boolean remover(Especialidade obj) {
        try {
            getEm().getTransaction().begin();
            getEm().remove(obj);
            getEm().getTransaction().commit();
            setMensagem("Objeto persistido com sucesso");
            return true;
        } catch (Exception e) {
            if (getEm().getTransaction().isActive() == false) {
                getEm().getTransaction().begin();
            }
            getEm().getTransaction().rollback();
            setMensagem("Erro ao remover objeto: " + Util.getMensagemErro(e));
            return false;
        }
    }

    public Especialidade localizar(Integer id) {
        return getEm().find(Especialidade.class, id);
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
