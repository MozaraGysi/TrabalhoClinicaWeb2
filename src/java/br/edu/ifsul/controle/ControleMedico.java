
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Medico;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable {

    private MedicoDAO<Medico> dao;
    private Medico objeto;
    private EspecialidadeDAO<Especialidade> daoespecial;
    
    public ControleMedico(){
        dao = new MedicoDAO<>();
        daoespecial = new EspecialidadeDAO<>();
    }
    
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medico();
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public MedicoDAO<Medico> getDao() {
        return dao;
    }

    public void setDao(MedicoDAO<Medico> dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getDaoespecial() {
        return daoespecial;
    }

    public void setDaoespecial(EspecialidadeDAO<Especialidade> daoespecial) {
        this.daoespecial = daoespecial;
    }

    }