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

	public void create(Endereco obj) {
		int id = super.getMaxId();
		obj.setId(id + 1);
		manager.store(obj);
	}
}