package com.hirezp.historico;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hirezp.ProxyProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Service
public class HitoricoService {

	@Autowired
	private ProxyProperties proxyProperties;

	MongoTemplate mongoTemplate = null;

	public ResponseEntity<?> carregar(Consulta consulta) throws Exception {

		mongoTemplate = getTemplate("hirez");
		List<MapsRet> mapas = CONSTANTES.mapas();
		if (consulta.getAtualizar() == true) {
			consultarExterno(consulta.getId(), 0);
		}
		List<Data> todasPart = consultarInterno(consulta.getId());

		//////////////////////////////////////////////////
		List<Players> lPlay = new ArrayList<>();
		todasPart.stream().map(m -> m.getPlayers()).forEach(f -> {
			lPlay.addAll(f);
		});
		Map<String, Long> mapCountPlay = lPlay.stream()
				.collect(Collectors.groupingBy(Players::getId, Collectors.counting()));
		List<Map.Entry<String, Long>> countOrdenado = new LinkedList<Map.Entry<String, Long>>(mapCountPlay.entrySet());
		ordenarMap(countOrdenado);
		List<AdverRet> adverListRet = countOrdenado.stream().map(m -> {
			AdverRet adver = new AdverRet();
			adver.setId(m.getKey());
			adver.setTotal(m.getValue().intValue());
			adver.setNome(lPlay.stream().filter(r -> r.getId().equalsIgnoreCase(m.getKey())).findFirst()
					.orElse(new Players()).getName());
			return adver;
		}).collect(Collectors.toList());

		todasPart.stream().forEach(tp -> {

			tp.getPlayers().stream().forEach(r -> {
				// encontra o pl atual
				List<AdverRet> pls = adverListRet.stream().filter(ad -> ad.getId().equalsIgnoreCase(r.getId()))
						.collect(Collectors.toList());
				Players pl = tp.getPlayers().stream().filter(f -> f.getId().equalsIgnoreCase(consulta.getId()))
						.findFirst().orElse(null);
				// se o ps é o 
				if (pl != null && pl.getTeam().equalsIgnoreCase(r.getTeam())) {
					// add. v
					if (r.getTeam().equalsIgnoreCase(tp.getWinning_team())) {
						pls.stream().forEach(o -> {
							o.addV();
						});
					} else {
						// add d
						pls.stream().forEach(o -> {
							o.addD();
						});
					}
				}else {
					// add. v
					if (r.getTeam().equalsIgnoreCase(tp.getWinning_team())) {
						pls.stream().forEach(o -> {
							o.addDC();
						});
					} else {
						// add d
						pls.stream().forEach(o -> {
							o.addVC();
						});
					}
				}

			});

		});

		///////////////////////////////////////////////////
		Map<String, Long> mapCountMapas = todasPart.stream().filter(f -> f.getMap() != null)
				.collect(Collectors.groupingBy(Data::getMap, Collectors.counting()));
		List<Map.Entry<String, Long>> countOrdenadoMap = new LinkedList<Map.Entry<String, Long>>(
				mapCountMapas.entrySet());

		ordenarMap(countOrdenadoMap);
		List<MapsRet> mapsRetList = countOrdenadoMap.stream().map(m -> {
			MapsRet map = new MapsRet();
			map.setNome(m.getKey());
			map.setTotal(m.getValue().intValue());
			MapsRet traducao = mapas.stream().filter(f -> f.getNome().equalsIgnoreCase(m.getKey())).findFirst()
					.orElse(new MapsRet());
			map.setNomePT(traducao.getNomePT());
			return map;
		}).collect(Collectors.toList());

		todasPart.stream().forEach(f -> {
			Optional<Players> findFirst = f.getPlayers().stream()
					.filter(d -> d.getId().equalsIgnoreCase(consulta.getId())).findFirst();
			if (findFirst.isPresent() && findFirst.get().getTeam().equalsIgnoreCase(f.getWinning_team())) {
				mapsRetList.stream().forEach(map -> {
					if (map.getNome() != null && map.getNome().equalsIgnoreCase(f.getMap())) {
						map.addVit();
					}
				});
			} else {
				mapsRetList.stream().forEach(map -> {
					if (map.getNome() != null && map.getNome().equalsIgnoreCase(f.getMap())) {
						map.addDer();
					}
				});
			}
		});

		///////////////////////////////////////////////////

		Player player = consultar(consulta.getId());

		//////////////////////////////////////////////////
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("player", player);
		ret.put("adver", adverListRet);
		ret.put("map", mapsRetList);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	private void ordenarMap(List<Map.Entry<String, Long>> countOrdenado) {
		Collections.sort(countOrdenado, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
	}

	private void consultarExterno(String id, Integer cursor) {
		try {

			RestTemplate restTemplateAuth = restTemplate();

			ResponseEntity<RetornoHistorico> response = restTemplateAuth.exchange(
					URI.create("https://api.paladins.guru/v3/profiles/" + id + "/matches?page=" + cursor),
					HttpMethod.GET, null, RetornoHistorico.class);

			RetornoHistorico body = response.getBody();

			body.getMatches().getData().stream().forEach(f -> {
				mongoTemplate.save(f, id);
				System.out.println(f.getMap());
			});
			if (body.getMatches().getCursor().getCurrent() < body.getMatches().getCursor().getMax()) {
				consultarExterno(id, body.getMatches().getCursor().getCurrent() + 1);
			}
		} catch (Exception e) {
		}

	}

	private Player consultar(String id) {
		try {
			RestTemplate restTemplateAuth = restTemplate();

			ResponseEntity<HashMap<String, Object>> response = restTemplateAuth.exchange(
					URI.create("https://api.paladins.guru/v3/profiles/" + id + "/summary"), HttpMethod.GET, null,
					new ParameterizedTypeReference<HashMap<String, Object>>() {
					});
			ObjectMapper objectMapper = new ObjectMapper();
			Object object = response.getBody().get("player");
			String s = objectMapper.writeValueAsString(object);
			Player p = objectMapper.readValue(s, Player.class);
			return p;
		} catch (Exception e) {
			return null;
		}
	}

	private List<Data> consultarInterno(String id) {
		List<Data> list = mongoTemplate.findAll(Data.class, id);
		return list;
	}

	public RestTemplate restTemplate() {

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		if (proxyProperties.getHabilitado()) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

			credentialsProvider.setCredentials(AuthScope.ANY,
					new NTCredentials(proxyProperties.getUser(), proxyProperties.getPassword(), null, null));
			HttpClientBuilder clientBuilder = HttpClientBuilder.create();

			clientBuilder.useSystemProperties();
			clientBuilder.setProxy(new HttpHost(proxyProperties.getHost(), proxyProperties.getPort()));
			clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
			CloseableHttpClient client = clientBuilder.build();
			factory.setHttpClient(client);
		}

		factory.setConnectTimeout(3000);
		factory.setReadTimeout(5000);
		factory.setConnectionRequestTimeout(3000);

		return new RestTemplate(factory);

	}

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	private MongoClient createMongo() throws Exception {
		return new MongoClient(new MongoClientURI(mongoUri));
	}

	public MongoTemplate getTemplate(String clienteDB) throws Exception {
		final MongoDbFactory factory = new SimpleMongoDbFactory(createMongo(), clienteDB);
		return new MongoTemplate(factory);
	}

}
