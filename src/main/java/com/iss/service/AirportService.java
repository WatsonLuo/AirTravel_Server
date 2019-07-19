package com.iss.service;

import com.iss.dao.AirportDao;
import com.iss.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    AirportDao airportDao;
    public int setAirport(Airport airport){return airportDao.setAirport(airport); }
}
