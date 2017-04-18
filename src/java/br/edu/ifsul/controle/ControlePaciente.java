
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controlePaciente")
@SessionScoped
public class ControlePaciente {
    private PacienteDAO dao;
    private Paciente objeto;
    
    public ControlePaciente(){
        dao = new PacienteDAO();
    }
    
    public String listar(){
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public String nova(){
        setObjeto(new Paciente());
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

    public PacienteDAO getDao() {
        return dao;
    }

    public void setDao(PacienteDAO dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }
}
