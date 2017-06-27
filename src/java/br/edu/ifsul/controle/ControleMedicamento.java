
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Medicamento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleMedicamento")
@ViewScoped
public class ControleMedicamento implements Serializable {

    private MedicamentoDAO<Medicamento> dao;
    private Medicamento objeto;
    
    public ControleMedicamento(){
        dao = new MedicamentoDAO<>();
    }
    
    public String listar(){
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medicamento();
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

    public MedicamentoDAO<Medicamento> getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO<Medicamento> dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }

    }