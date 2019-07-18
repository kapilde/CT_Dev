package handson.impl;

import java.io.IOException;
import java.util.Properties;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.client.SphereClientFactory;

public class ClientService {
    /**
     * Creates a blocking sphere client
     * @return Sphere client
     * @throws IOException
     */
	 public static SphereClient createSphereClient() throws IOException {
	        final SphereClientConfig clientConfig = loadCTPClientConfig();
	        //
	        final SphereClientFactory factory = SphereClientFactory.of();
	        SphereClient client = factory.createClient(clientConfig.getProjectKey(),
	                        clientConfig.getClientId(), 
	                        clientConfig.getClientSecret());
			return client;  
	        
	        

	    }

	    /**
	     * Sets a sphere client configuration
	     * @return sphere client configuration
	     * @throws IOException
	     */
	    private static SphereClientConfig loadCTPClientConfig() throws IOException {
	        final Properties prop = new Properties();
	        prop.load(ClientService.class.getResourceAsStream("/dev.properties"));
	       // SphereClientConfig config = SphereClientConfig.of(prop.getProperty("projectKey"), prop.getProperty("clientId"), prop.getProperty("clientSecret"), prop.getProperty("apiUrl"), prop.getProperty("authUrl"));
	        SphereClientConfig config = SphereClientConfig.ofProperties(prop, "ct");
	        
	        // String projectkey=prop.getProperty("projectKey");
	        //TODO 1.2 Create the configuration for the sphere client
	        return config;

	    }
}