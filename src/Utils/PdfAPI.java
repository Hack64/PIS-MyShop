package Utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfAPI implements IPdfAPI{
    @Override
    public int creaPdf(List<String> lines, String outFile) {
        try( PDDocument doc = new PDDocument() ){
            PDPage page = new PDPage();
            page.setMediaBox(PDRectangle.A4);
            PDRectangle pageSize = page.getMediaBox();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;
            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(font, 20);
                contents.newLineAtOffset(40, 800);
                contents.showText(lines.get(0));
                contents.setFont(font, 14);
                contents.newLineAtOffset(0, -75);
                for (String s:lines.subList(1,lines.size()-1)){
                    contents.showText(s);
                    contents.newLineAtOffset(0, -25);
                }
                contents.newLineAtOffset(0, -20); //footer
                contents.showText(lines.get(lines.size()-1));
                contents.endText();
            }
            doc.save(outFile);
            System.out.println("File pdf generato con PdfBox salvato in: "+outFile);
        } catch (IOException e){
            e.printStackTrace();
        }

        return 0;
    }
}
