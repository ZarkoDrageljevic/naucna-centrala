package ftn.uns.ac.rs.naucnacentrala.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {


//    @Bean
//    public Client client() throws UnknownHostException {
//        Settings elasticsearchSettings = Settings.EMPTY;
//        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        return client;
//    }
//
//    @Bean(name = "elasticsearch_template")
//    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(client());
//    }
}