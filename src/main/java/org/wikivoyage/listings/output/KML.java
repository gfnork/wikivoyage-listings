package org.wikivoyage.listings.output;

import org.wikivoyage.listings.utils.XMLSimpleNode;
import org.wikivoyage.listings.entity.WikivoyagePOI;
import org.wikivoyage.listings.utils.XMLSimpleNodeException;

public class KML extends NavigationXMLOutputFormat {
    @Override
    public XMLSimpleNode createXml(WikivoyagePOI[] pois) throws XMLSimpleNodeException
    {
        XMLSimpleNode kmlNode = new XMLSimpleNode("kml")
                .attrib("xmlns", "http://www.opengis.net/kml/2.2");
        XMLSimpleNode documentNode = new XMLSimpleNode(kmlNode, "Document");
        for (WikivoyagePOI poi: pois) {
            XMLSimpleNode placemarkNode = new XMLSimpleNode(documentNode, "Placemark");
            placemarkNode.textChild("name", poi.getTitle());
            placemarkNode.textChild("description", poi.getDescription());
            XMLSimpleNode pointNode = new XMLSimpleNode(placemarkNode, "Point");
            pointNode.textChild("coordinates", poi.getLongitude() + "," + poi.getLatitude());
        }

        return kmlNode;
    }

    @Override
    public String getDefaultExtension() {
        return ".kml";
    }
}
