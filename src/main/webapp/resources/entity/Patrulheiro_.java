package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-09-09T20:57:18.552-0300")
@StaticMetamodel(Patrulheiro.class)
public class Patrulheiro_ extends Usuario_ {
	public static volatile SingularAttribute<Patrulheiro, String> cep;
	public static volatile SingularAttribute<Patrulheiro, String> ender;
	public static volatile SingularAttribute<Patrulheiro, String> bairro;
	public static volatile SingularAttribute<Patrulheiro, Integer> munic;
	public static volatile SingularAttribute<Patrulheiro, Integer> uf;
}
