package br.com.integration.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteTest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest()
		.consumes("application/json")
		.produces("application/json")
		
		.post("test")
		.to("direct:vempraca");
		
		
		from("direct:vempraca")
		.routeId("vempraca")
		.process(ex -> {
			ex.getIn().setBody("filho da puta");
		})
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("200"));
	}

}
