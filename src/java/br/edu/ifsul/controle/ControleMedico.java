
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.modelo.Medico;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleMedico")
@SessionScoped
public class ControleMedico {
    private MedicoDAO dao;
    private Medico objeto;
    private EspecialidadeDAO daoEspecialidade;
    
    public ControleMedico(){
         dao = new MedicoDAO();
         daoEspecialidade = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public String nova(){
        setObjeto(new Medico());
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
    
    public MedicoDAO getDao() {
        return dao;
    }

    public void setDao(MedicoDAO dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }
    
    public EspecialidadeDAO getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
