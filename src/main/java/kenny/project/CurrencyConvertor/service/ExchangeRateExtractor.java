package kenny.project.CurrencyConvertor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ExchangeRateExtractor {

    @Value("${ratesInput.filepath}")
    private String inputPath;

    public Map<String, BigDecimal> extract() throws IOException, SAXException,
            ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(inputPath));
        doc.getDocumentElement().normalize();

        Map<String, BigDecimal> rates = new HashMap<>();

        NodeList nodeList = doc.getElementsByTagName("Cube");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap attrList = node.getAttributes();

            Node currencyAttribute = attrList.getNamedItem("currency");
            Node rateAttribute = attrList.getNamedItem("rate");

            if (attrList.getLength() == 2
                    && !Objects.isNull(currencyAttribute)
                    && !Objects.isNull(rateAttribute)) {
                String currency = currencyAttribute.getNodeValue();
                String rate = rateAttribute.getNodeValue();

                rates.put(currency, new BigDecimal(rate));
            }
        }
        return rates;
    }
}
