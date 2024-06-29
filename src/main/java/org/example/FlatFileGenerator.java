package org.example;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.example.flatfile.Body;
import org.example.flatfile.Header;
import org.example.flatfile.Trailer;

import java.util.Arrays;
import java.util.List;

import java.io.*;

public class FlatFileGenerator {
    public static void main(String[] args) {

        StreamFactory factory = StreamFactory.newInstance();
        factory.loadResource("mapping.xml");

        BeanWriter out = null;
        try {
            out = factory.createWriter("flatFile", new File("output.txt"));

            // Write Header
            Header header = new Header();
            header.setRecordType("H");
            header.setPadding1("");
            header.setFileType("FLAT_FILE");
            header.setPadding2("");
            out.write(header);

            // Write Body
            List<Body> bodies = Arrays.asList(
                    createBody("6813162459", "RM2.00"),
                    createBody("2039229524", "RM10.00"),
                    createBody("2299488320", "RM5.00"),
                    createBody("3898335898", "RM1255.0")
            );

            for (Body body : bodies) {
                out.write(body);
            }

            // Write Trailer
            Trailer trailer = new Trailer();
            trailer.setRecordType("T");
            trailer.setPadding1("");
            trailer.setFileType("FLAT_FILE");
            trailer.setPadding2("");
            out.write(trailer);

        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    private static Body createBody(String referenceNo, String amount) {
        Body body = new Body();
        body.setReferenceNo(referenceNo);
        body.setAmount(amount);
        return body;
    }
}