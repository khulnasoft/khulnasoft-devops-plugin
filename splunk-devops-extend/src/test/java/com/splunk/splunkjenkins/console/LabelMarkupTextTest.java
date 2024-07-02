package com.splunk.splunkjenkins.console;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static junit.framework.TestCase.assertEquals;

public class LabelMarkupTextTest{
    @Test
    public void testMarkup() throws IOException {
        LabelMarkupText labelMarkupText=new LabelMarkupText();
        labelMarkupText.addMarkup(0,0,
                "<span href=\"url\">","test &nbsp; </span>");
        OutputStream outputStream=new ByteArrayOutputStream();
        labelMarkupText.write(outputStream);
        String outputs=outputStream.toString();
        assertEquals("href=url ", outputs);
        // test enclosing labels
        labelMarkupText.addMarkup(0,0,"<span class=\"pipeline-new-node\" nodeId=\"5\" startId=\"5\" enclosingId=\"3\" label=\"Branch: a\">","</span>");
        labelMarkupText.addMarkup(0,0,"<span class=\"pipeline-new-node\" nodeId=\"7\" enclosingId=\"5\">","&nbsp; </span>");
        outputStream=new ByteArrayOutputStream();
        labelMarkupText.write(outputStream);
        labelMarkupText.writePreviousLabel(outputStream);
        outputs=outputStream.toString();
        assertEquals("parallel_label=\"a\" ", outputs);
    }
}