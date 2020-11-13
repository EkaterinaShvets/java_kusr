package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetIpLocation;
import org.testng.annotations.Test;

public class GeoIPServiceTests {

  @Test
  public void testMyIp (){
    new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.113.50.40");
  }
}
