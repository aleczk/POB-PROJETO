/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package dao;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.*;


public class DAOEndereco extends DAO<Endereco> {

	public Endereco read(Object chave) {
		String nomeEndereco = (String) chave;
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("nome").constrain(nomeEndereco);
		List<Endereco> resultados = q.execute();

		return (resultados.size() > 0) ? resultados.get(0) : null;
	}

//	public List<Endereco> consulta(String numeroEndereco) {
//		Query q = manager.query();
//		q.constrain(Endereco.class);
//		// q.constrain(new Filtro1(arg1, arg2)) // a ser implementado
//		List<Endereco> resultados = q.execute();
//		
//		return resultados;

	public void Endereco(Endereco object) {
		Endereco e = object;
		int id = super.getMaxId();
		id++;
		e.setId(id);
		manager.store(e);
	}

	public List<Endereco> consulta() {
		Query q = manager.query();
		q.constrain(Endereco.class);
//		q.descend("enderecos").constraints(new Filtro1());
		List<Endereco> resultados = q.execute();

		return resultados;

	}
}