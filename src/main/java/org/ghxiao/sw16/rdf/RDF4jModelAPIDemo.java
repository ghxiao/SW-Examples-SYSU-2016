package org.ghxiao.sw16.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;

public class RDF4jModelAPIDemo {
    public static void main(String[] args) {
        ValueFactory factory = SimpleValueFactory.getInstance();
        IRI bob = factory.createIRI("http://example.org/bob");
        IRI name = factory.createIRI("http://example.org/name");
        Literal bobsName = factory.createLiteral("Bob");
        Statement nameStatement = factory.createStatement(bob, name, bobsName);
        Statement typeStatement = factory.createStatement(bob, RDF.TYPE, FOAF.PERSON);

        // create a new Model to put statements in
        Model model = new LinkedHashModel();
        // add an RDF statement
        model.add(nameStatement);
        model.add(typeStatement);

        model.forEach(System.out::println);

        //model.forEach(System.out::println);

        //System.out.println(model);
    }
}
