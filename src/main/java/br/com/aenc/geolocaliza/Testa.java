package br.com.aenc.geolocaliza;

public class Testa {

	public static void main(String[] args) {

		GeoCoordinate geoNit = new GeoCoordinate(-22.8858975,
				-43.115221099999985);
		GeoCoordinate geoRio = new GeoCoordinate(-22.9068467,
				-43.17289649999998);
		GeoCoordinate geoSG = new GeoCoordinate(-22.8272883, -43.06376460000001);
		
		double distancia = geoNit.distanceInKm(geoRio);
		double distNitSg = geoNit.distanceInKm(geoSG);
		
		System.out.println("Distancia entre Niterói e Rio: " + distancia);
		
		System.out.println("Distancia entre Niterói e Sao Goncalo: " + distNitSg);
		
	}
}
