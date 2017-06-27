
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controlePaciente")
@ViewScoped
public class ControlePaciente implements Serializable {

    private PacienteDAO<Paciente> dao;
    private Paciente objeto;
   
    public ControlePaciente(){
        dao = new PacienteDAO<>();
    }
    
    public String listar(){
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Paciente();
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

    public PacienteDAO<Paciente> getDao() {
        return dao;
    }

    public void setDao(PacienteDAO<Paciente> dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }

    }