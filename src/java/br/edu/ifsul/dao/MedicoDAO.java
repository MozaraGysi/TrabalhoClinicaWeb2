
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class MedicoDAO implements Serializable{
   private String mensagem = "";
   private EntityManager em;
   
   public MedicoDAO(){
       em = EntityManagerUtil.getEntityManager();
   }
   
   public List<Medico> getLista() {
        return getEm().createQuery("from Medico order by crm").getResultList();
    }

    public boolean salvar(Medico obj) {
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

    public boolean remover(Medico obj) {
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

    public Medico localizar(Integer id) {
        return getEm().find(Medico.class, id);
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
