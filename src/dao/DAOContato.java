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


public class DAOContato extends DAO<Contato> {

	public Contato read(Object chave) {
		String nomeContato = (String) chave;
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("nome").constrain(nomeContato);
		List<Contato> resultados = q.execute();

		return (resultados.size() > 0) ? resultados.get(0) : null; }
	
	public List<Contato> consultaA(String nomeBairro) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("endereco").descend("bairro").constrain(nomeBairro);
		return q.execute();
	}
}