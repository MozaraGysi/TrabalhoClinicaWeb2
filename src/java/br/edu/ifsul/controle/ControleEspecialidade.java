package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade {
    private EspecialidadeDAO dao;
    private Especialidade objeto;
    
    public ControleEspecialidade(){
        dao = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String nova(){
        setObjeto(new Especialidade());
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
    
    public EspecialidadeDAO getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
    
}
