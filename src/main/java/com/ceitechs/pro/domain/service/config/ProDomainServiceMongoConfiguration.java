/**
 * 
 */
package com.ceitechs.pro.domain.service.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * @author vctrowino
 *
 */
@Configuration
@EnableMongoRepositories("com.ceitechs.pro.domain.service.repositories")
public class ProDomainServiceMongoConfiguration extends AbstractMongoConfiguration{
	private final static String HOSTS_SEPARATOR = ",";
    private final static String HOST_PORT_SEPARATOR = ":";
    
	@Value("${pro.domain.service.db.password}")
    private String dbPassword;

    @Value("${pro.domain.service.db.user}")
    private String dbuser;
    
	@Value("${pro.domain.service.db.host.name}")
	private String host;
	
	@Value("${pro.domain.service.db.name}")
    private String dbName;

    @Value("${pro.domain.service.bucket.name}")
    private String bucketName;

	@Override
	protected String getDatabaseName() {
		return dbName;
	}
	
	@Override
	protected String getMappingBasePackage() {
		return "com.ceitechs.pro.domain.service.domain";
	}

	@Override
	public Mongo mongo() throws Exception {
		List<ServerAddress> addresses = Stream.of(host.split(HOSTS_SEPARATOR)).map(addr-> {
			String[] hostAndPort = addr.split(HOST_PORT_SEPARATOR);
			return new ServerAddress(hostAndPort[0],Integer.valueOf(hostAndPort[1]));
		}).collect(Collectors.toList());
		
		MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(dbuser, 
				dbName, dbPassword.toCharArray());
		Mongo mongo = new MongoClient(addresses, Arrays.asList(mongoCredential));
		mongo.setWriteConcern(WriteConcern.MAJORITY);
		return mongo;
	}
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), bucketName);
	}
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}
}
