package org.ghxiao.sw16.sparql;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.repository.util.Repositories;

import java.util.List;

public class SPARQLQueryDemo {

    public static void main(String[] args) throws Exception{
        String sparqlEndpoint = "http://dblp.l3s.de/d2r/sparql";
        Repository repo = new SPARQLRepository(sparqlEndpoint);
        repo.initialize();

        try (RepositoryConnection conn = repo.getConnection()) {
            String queryString = "PREFIX swrc: <http://swrc.ontoware.org/ontology#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX map: <file:///home/diederich/d2r-server-0.3.2/dblp-mapping.n3#>\n" +
                    "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                    "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                    "SELECT (COUNT(?D) AS ?CountD) ?X  ?L WHERE {\n" +
                    "       ?D dc:creator <http://dblp.l3s.de/d2r/resource/authors/Guohui_Xiao>.\n" +
                    "?D dc:creator ?X .\n" +
                    "?X rdfs:label ?L\n" +
                    "FILTER (?X != <http://dblp.l3s.de/d2r/resource/authors/Guohui_Xiao>)\n" +
                    "       }\n" +
                    "GROUP BY ?X ?L\n" +
                    "ORDER BY DESC(?CountD)";

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
