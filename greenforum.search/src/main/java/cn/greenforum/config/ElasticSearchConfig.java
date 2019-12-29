package cn.greenforum.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="greenforum.es")
public class ElasticSearchConfig{
	private List<String> nodes;
	
	//初始化TransportClient
	@Bean
	public TransportClient iniTransportClient() throws UnknownHostException {
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
		for (String node : nodes) {
			String host=node.split(":")[0];
			int port=Integer.parseInt(node.split(":")[1]);
			InetSocketTransportAddress address=new InetSocketTransportAddress(InetAddress.getByName(host),port);
			client.addTransportAddress(address); 
		}
		return client;
	}

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
}