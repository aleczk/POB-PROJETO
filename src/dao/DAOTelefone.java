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


public class DAOTelefone extends DAO<Telefone> {

	public Telefone read(Object chave) {
		String numTelefone = (String) chave;
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain(numTelefone);
		List<Telefone> resultados = q.execute();

		return (resultados.size() > 0) ? resultados.get(0) : null; }

	public List<Telefone> consultaB() {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain("3").startsWith(true);
		return q.execute();
	}
}