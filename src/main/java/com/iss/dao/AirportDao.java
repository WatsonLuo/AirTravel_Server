package com.iss.dao;

import com.iss.entity.Airport;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface AirportDao {
    @Insert("insert into flightinfo values(#{code},#{frcd},#{apat},#{cnnm},#{ennm},#{aiso},#{apsn})")
    public int setAirport(Airport airport);
//    public List<Airport> getAllAirport();
}
