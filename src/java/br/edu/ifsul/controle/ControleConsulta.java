
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Receituario;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable{
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    private PacienteDAO<Paciente> daopaciente;
    private MedicoDAO<Medico> daomedico;
    private Exame exame;
    private Boolean novoExame;
    private Receituario receita;
    private Boolean novaReceita;
    
    public ControleConsulta(){
        dao = new ConsultaDAO<>();
        daopaciente = new PacienteDAO<>();
        daomedico = new MedicoDAO<>();
    }
    
    public void relatorio(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("Consl", parametros,
                dao.getListaTodos());
    }  
    
    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Consulta();
        objeto.setData(Calendar.getInstance());
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
    
    public void novoExame() {
        exame = new Exame();
        novoExame = true;
    }
     
    public void alterarExame(int index) {
        System.out.println("IDX alterar: " + index);
        exame = objeto.getExame().get(index);
        novoExame = false;
    }
    
    public void salvarExame() {
        if (novoExame) {
            objeto.adicionarExame(exame);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerExame(int index) {
        System.out.println("IDX remover: " + index);
        objeto.removerExame(index);
        Util.mensagemInformacao("Exame removido com sucesso");
    }
    
    public void novaReceita() {
        receita = new Receituario();
        novaReceita = true;
    }
     
    public void alterarReceita(int index) {
        System.out.println("IDX alterar: " + index);
        receita = objeto.getReceituario().get(index);
        novaReceita = false;
    }
    
    public void salvarReceita() {
        if (novaReceita) {
            objeto.adicionarReceita(receita);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerReceita(int index) {
        System.out.println("IDX remover: " + index);
        objeto.removerReceita(index);
        Util.mensagemInformacao("Receita removida com sucesso");
    }

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public PacienteDAO<Paciente> getDaopaciente() {
        return daopaciente;
    }

    public void setDaopaciente(PacienteDAO<Paciente> daopaciente) {
        this.daopaciente = daopaciente;
    }

    public MedicoDAO<Medico> getDaomedico() {
        return daomedico;
    }

    public void setDaomedico(MedicoDAO<Medico> daomedico) {
        this.daomedico = daomedico;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

    public Receituario getReceita() {
        return receita;
    }

    public void setReceita(Receituario receita) {
        this.receita = receita;
    }

    public Boolean getNovaReceita() {
        return novaReceita;
    }

    public void setNovaReceita(Boolean novaReceita) {
        this.novaReceita = novaReceita;
    }
    
}
