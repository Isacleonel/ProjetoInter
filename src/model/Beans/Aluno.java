package model.Beans;

import java.sql.Date;


public class Aluno {
	
	private int CodigoAluno, CidadeAluno;
	private String NomeAluno, SexoAluno, CpfAluno="", IdentidadeAluno="", ProfissaoAluno="", EstadoAluno="", BairroAluno="", EnderecoAluno="", CelularAluno="", FoneAluno="", EmailAluno="", StatusAluno, NomeCidadeAluno;
	private Date NascimentoAluno, CadastroAluno, CancelamentoAluno=null;
	
	
	public int getCodigo() {
		return CodigoAluno;
	}
	public void setCodigo(int codigo) {
		this.CodigoAluno = codigo;
	}
	public String getNome() {
		return NomeAluno;
	}
	public void setNome(String nome) {
		this.NomeAluno = nome;
	}
	public String getSexo() {
		return SexoAluno;
	}
	public void setSexo(String sexo) {
		this.SexoAluno = sexo;
	}
	public String getCpf() {
		return CpfAluno;
	}
	public void setCpf(String cpf) {
		this.CpfAluno = cpf;
	}
	public String getIdentidade() {
		return IdentidadeAluno;
	}
	public void setIdentidade(String identidade) {
		this.IdentidadeAluno = identidade;
	}
	public String getProfissao() {
		return ProfissaoAluno;
	}
	public void setProfissao(String profissao) {
		this.ProfissaoAluno = profissao;
	}
	public Date getNascimento() {
		return NascimentoAluno;
	}
	public void setNascimento(Date nascimento) {
		this.NascimentoAluno = nascimento;
	}
	public String getEstado() {
		return EstadoAluno;
	}
	public void setEstado(String estado) {
		this.EstadoAluno = estado;
	}
	public int getCidade() {
		return CidadeAluno;
	}
	public void setCidade(int cidade) {
		this.CidadeAluno = cidade;
	}
	public String getNomeCidade() {
		return NomeCidadeAluno;
	}
	public void setNomeCidade(String cidade) {
		this.NomeCidadeAluno = cidade;
	}
	
	public String getBairro() {
		return BairroAluno;
	}
	public void setBairro(String bairro) {
		this.BairroAluno = bairro;
	}
	public String getEndereco() {
		return EnderecoAluno;
	}
	public void setEndereco(String endereco) {
		this.EnderecoAluno = endereco;
	}
	public String getCelular() {
		return CelularAluno;
	}
	public void setCelular(String celular) {
		this.CelularAluno = celular;
	}
	public String getFone() {
		return FoneAluno;
	}
	public void setFone(String fone) {
		this.FoneAluno = fone;
	}
	public String getEmail() {
		return EmailAluno;
	}
	public void setEmail(String email) {
		this.EmailAluno = email;
	}
	public Date getCadastro() {
		return CadastroAluno;
	}
	public void setCadastro(Date cadastro) {
		this.CadastroAluno = cadastro;
	}
	public String getStatus() {
		return StatusAluno;
	}
	public void setStatus(String status) {
		this.StatusAluno = status;
	}
	public Date getCancelamento() {
		return CancelamentoAluno;
	}
	public void setCancelamento(Date cancelamento) {
		this.CancelamentoAluno = cancelamento;
	}
	

		
	
	

}
