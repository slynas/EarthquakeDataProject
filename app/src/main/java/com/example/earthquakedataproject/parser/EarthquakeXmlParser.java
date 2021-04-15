package com.example.earthquakedataproject.parser;


import com.example.earthquakedataproject.model.Earthquake;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class EarthquakeXmlParser {

    private List<Earthquake> earthquakes = new ArrayList<Earthquake>();
    private Earthquake earthquake = new Earthquake();
    private String text;

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public List<Earthquake> parseLocationMagnitude (InputStream inputStream) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  parser = factory.newPullParser();

            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            earthquake = new Earthquake();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            String location = getLocationFromDescription(earthquake.getDescription());
                            Double magnitude = Double.parseDouble(getMagnitudeFromDescription(earthquake.getDescription()));

                            Earthquake earthquakeLocationMagnitude = new Earthquake(location, magnitude);

                            earthquakes.add(earthquakeLocationMagnitude);
                        }  else if (tagname.equalsIgnoreCase("description")) {
                            earthquake.setDescription(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {e.printStackTrace();}
        return earthquakes;
    }

    public List<Earthquake> parseDescription(InputStream inputStream) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  parser = factory.newPullParser();

            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            earthquake = new Earthquake();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            String date = getDateFromDescription(earthquake.getDescription());
                            String location = getLocationFromDescription(earthquake.getDescription());
                            Double magnitude = Double.parseDouble(getMagnitudeFromDescription(earthquake.getDescription()));
                            String latLong = getLatLongFromDescription(earthquake.getDescription());
                            String depth = getDepthFromDescription(earthquake.getDescription());

                            Earthquake earthquakeLocationMagnitude = new Earthquake(location, magnitude, latLong, depth, date);

                            earthquakes.add(earthquakeLocationMagnitude);
                        }  else if (tagname.equalsIgnoreCase("description")) {
                            earthquake.setDescription(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {e.printStackTrace();}
        return earthquakes;
    }

    private String getDepthFromDescription(String text) {
        String result = null;
        String[] descriptionArray = text.split(";");
        for(String s : descriptionArray) {
            if (s.startsWith(" Depth: ")) {
                result = s.replace(" Depth: ", "");
            }
        }
        return result;
    }

    private String getLatLongFromDescription(String text) {
        String result = null;
        String[] descriptionArray = text.split(";");
        for(String s : descriptionArray) {
            if (s.startsWith(" Lat/long: ")) {
                result = s.replace(" Lat/long: ", "");
            }
        }
        return result;
    }

    private String getMagnitudeFromDescription(String text) {
        String result = null;
        String[] descriptionArray = text.split(";");
        for(String s : descriptionArray) {
            if (s.startsWith(" Magnitude: ")) {
                result = s.replace(" Magnitude: ", "");
            }
        }
        return result;
    }

    private String getLocationFromDescription(String text) {
        String result = null;
        String[] descriptionArray = text.split(";");
        for(String s : descriptionArray) {
            if (s.startsWith(" Location: ")) {
                result = s.replace(" Location: ", "");
            }
        }
        return result;
    }

    private String getDateFromDescription(String text) {
        String result = null;
        String[] descriptionArray = text.split(";");
        for (String s : descriptionArray) {
            if (s.startsWith("Origin date/time: ")) {
                result = s.replace("Origin date/time: ", "");
            }
        }
        return result;
    }

}
