
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Medicamento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleMedicamento")
@SessionScoped
public class ControleMedicamento {
    private MedicamentoDAO dao;
    private Medicamento objeto;
    
    public ControleMedicamento(){
        dao = new MedicamentoDAO();
    }
    
    public String listar(){
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public String nova(){
        setObjeto(new Medicamento());
        return "formulario";
    }
    
    public String salvar(){
        if(getDao().salvar(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar";
        }else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try{
            setObjeto(getDao().localizar(id));
            return "formulario";
        }catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if(getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        }else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    

    public MedicamentoDAO getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }
    
}
