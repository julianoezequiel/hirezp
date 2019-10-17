package com.hirezp.historico;

import java.util.ArrayList;
import java.util.List;

public class CONSTANTES {

	public static List<MapsRet> mapas(){
		List<MapsRet> list = new ArrayList<>();
		list.add(new MapsRet("Ranked Ascension Peak","Pico da Ascensão"));
		list.add(new MapsRet("Ranked Brightmarsh","Brejo Brilhante"));
		list.add(new MapsRet("Ranked Shattered Desert","Deserto Destruido"));
		list.add(new MapsRet("Ranked Jaguar Falls","Cataratas da Onça"));
		list.add(new MapsRet("Ranked Serpent Beach","Praia da Serpente"));
		list.add(new MapsRet("Ranked Fish Market","Mercadão de Peixes"));
		list.add(new MapsRet("Ranked Frozen Guard","Guarda Gélida"));
		list.add(new MapsRet("",""));
		list.add(new MapsRet("",""));
		list.add(new MapsRet("",""));
		
		return list;
	}
	
}
