/*----------------------------------------------------------------------------------------
 *   This file is part of Camunda Traverser program.
 *
 *   Camunda Traverser program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Camunda Traverser program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Camunda Traverser program.  If not, see <http://www.gnu.org/licenses/>.
 *---------------------------------------------------------------------------------------*/

package net.davidesalerno.camunda.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.davidesalerno.camunda.dto.Workflow;

/**
* Default implementation of the Fetcher interface util class that it is used to manage the workflow fetching using the java HttpClient
* 
* @author Davide Salerno
* 
*/
public class FetcherImpl implements Fetcher{

    private static final Logger logger = LogManager.getLogger(FetcherImpl.class);

    private final static String DEFAULT_CAMUNDA_FLOW_URL = "https://gist.githubusercontent.com/davidesalerno/5933f80186d99285debe2cb1d359932f/raw/1c4e71e8e0443330dd94960edd53ec90e1244748/invoice_process_diagram.json";

    private HttpClient client;
    private ObjectMapper objectMapper = new ObjectMapper();

    public FetcherImpl() {
        this.client = defaultHTTPClient();
    }

    public FetcherImpl(final HttpClient client) {
        this.client = client;
    }

    private static HttpClient defaultHTTPClient() {
        logger.debug("Creating default HTTP Client with 500 ms timeout");
        return HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(500))
                .build();
    }

    private static HttpRequest buildHTTPRequest(final String uri) {
        HttpRequest request = null;
        try {
            logger.debug("Creating default HTTP Request with URL {}", DEFAULT_CAMUNDA_FLOW_URL);
            request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();
        } catch (URISyntaxException usx) {
            logger.error("Unable to create default HTTP Request with URL {}", DEFAULT_CAMUNDA_FLOW_URL);
        }
        return request;
    }

    private HttpResponse<String> sendRequest(final HttpRequest request) throws IOException, InterruptedException {
        logger.debug("Sending HTTP request to retrieve JSON object");
        HttpResponse<String> response = null;
        if(client==null || request==null){
             logger.error("Unable to retrieve the workflow definition due to undefined client and/or request");
             return response;
        }
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug("Status {}", response.statusCode());
        logger.debug("Response {}", response.body());
        return response;
    }

    private Workflow toWorkflow(final HttpResponse<String> response) throws JsonMappingException, JsonProcessingException {
        logger.debug("Converting HTTP response to workflow Object");
        return objectMapper.readValue(response.body(), Workflow.class);
    }

    public Workflow retrieveWorkflow() throws IOException, InterruptedException{
        return retrieveWorkflow(DEFAULT_CAMUNDA_FLOW_URL);
    }

    public Workflow retrieveWorkflow(final String uri) throws IOException, InterruptedException{
        HttpRequest request = buildHTTPRequest(uri);
        HttpResponse<String> response = sendRequest(request);
        return toWorkflow(response);
    }
}
