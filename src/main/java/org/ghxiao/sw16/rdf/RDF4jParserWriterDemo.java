package org.ghxiao.sw16.rdf;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RDF4jParserWriterDemo {
    public static void main(String[] args) throws IOException {
        java.net.URL documentUrl = new URL("http://xmlns.com/foaf/spec/index.rdf");
        InputStream inputStream = documentUrl.openStream();

        RDFParser rdfParser = Rio.createParser(RDFFormat.RDFXML);
        Model model = new LinkedHashModel();

        rdfParser.setRDFHandler(new StatementCollector(model));
        //final StatementCounter counter = new StatementCounter();
        //rdfParser.setRDFHandler(counter);
        try {
            rdfParser.parse(inputStream, documentUrl.toString());
        }
        catch (IOException | RDFParseException | RDFHandlerException e) {
            // handle IO problems (e.g. the file could not be read)
        }

        //System.out.println(counter.getCountedStatements());
        model.forEach(System.out::println);

        FileOutputStream out = new FileOutputStream("foaf.ttl");
        Rio.write(model, out, RDFFormat.TURTLE);

    }
}
