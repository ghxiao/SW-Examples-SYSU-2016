package org.ghxiao.sw16.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.RDFCollections;

import java.util.Arrays;
import java.util.List;

public class RDF4jCollectionAPIDemo {
    public static void main(String[] args) {
        String ns = "http://example.org/";
        ValueFactory vf = SimpleValueFactory.getInstance();
// IRI for ex:favoriteLetters
        IRI favoriteLetters = vf.createIRI(ns, "favoriteLetters");
// IRI for ex:John
        IRI john = vf.createIRI(ns, "John");
// create a list of letters
        List<Literal> letters = Arrays.asList(vf.createLiteral("A"), vf.createLiteral("B"), vf.createLiteral("C"));
// create a head resource for our list
        Resource head = vf.createBNode();
// convert our list and add it to a newly-created Model
        Model aboutJohn = RDFCollections.asRDF(letters, head, new LinkedHashModel());
// set the ex:favoriteLetters property to link to the head of the list
        aboutJohn.add(john, favoriteLetters, head);
        // ex:john ex:favoriteLetters ('A' 'B' 'C')
        aboutJohn.forEach(System.out::println);
    }
}
