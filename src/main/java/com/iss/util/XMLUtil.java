package com.iss.util;

import com.iss.dao.AirportDao;
import com.iss.entity.Airport;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XMLUtil {
    public static void main(String[] args) {
        try{
            String str="C:\\Users\\Dell\\Desktop\\msg_log0601-0608\\20190601\\BASE-APUE-20190601000354.xml";

            File file=new File(str);
            SAXReader reader=new SAXReader();
            Document document=reader.read(file.getAbsoluteFile());
            Element root=document.getRootElement();
//            System.out.println(root.getName());

            Airport airport=new Airport();
            List list=root.elements();
            for(Object object:list){
                Element e=(Element)object;
                List a=e.elements();
                for(Object o:a) {
                    Element ee = (Element) o;
                    if(ee.getName().equals("CODE")){
                        airport.setCode(ee.getText());
                    }
                    if(ee.getName().equals("FRCD")){
                        airport.setFrcd(ee.getText());
                    }
                    if(ee.getName().equals("APAT")){
                        airport.setApat(ee.getText());
                    }
                    if(ee.getName().equals("CNNM")){
                        airport.setCnnm(ee.getText());
                    }
                    if(ee.getName().equals("ENNM")){
                        airport.setEnnm(ee.getText());
                    }
                    if(ee.getName().equals("AISO")){
                        airport.setAiso(ee.getText());
                    }
                    if(ee.getName().equals("APSN")){
                        airport.setApsn(ee.getText());
                    }
                }
            }
            AirportDao airportDao = null;
            airportDao.setAirport(airport);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
