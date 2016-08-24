package org.ghxiao.sw16.rdfs;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.File;

public class RDFSSPARQLQueryDemo {

    public static void main(String[] args) throws Exception{
//        Repository repo = new SailRepository(new MemoryStore());
          Repository repo = new SailRepository(
                new ForwardChainingRDFSInferencer(
                        new MemoryStore()));
        repo.initialize();

        try (RepositoryConnection conn = repo.getConnection()) {

            conn.add(new File("src/main/resources/animal.ttl"), null, RDFFormat.TURTLE);

            String queryString = "PREFIX swrc: <http://swrc.ontoware.org/ontology#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX ex:  <http://ex.org/> \n" +
                    "SELECT * {ex:xiao rdf:type ?Type}";
                    //"SELECT * {?x rdfs:subClassOf ?y} ";

            //List<BindingSet> results = Repositories.tupleQuery(repo, queryString, r -> QueryResults.asList(r));
            // Repositories.tupleQuery(repo, queryString, r -> System.out.println(r));

            //System.out.println(results);

            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    BindingSet bindingSet = result.next();
                    System.out.println(bindingSet);
//                    Value countD = bindingSet.getValue("CountD");
//                    Value x = bindingSet.getValue("X");
//                    Value l = bindingSet.getValue("L");
//                    System.out.println("countD: " +countD + "x: " +x + "l: " + l);
//                     do something interesting with the values here...
                }
            }
        }
    }
}
