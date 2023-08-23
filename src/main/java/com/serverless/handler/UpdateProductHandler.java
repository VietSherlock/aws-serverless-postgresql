package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.serverless.response.Response;
import com.serverless.request.ProductRequest;
import com.serverless.service.ProductService;

import java.util.Map;

public class UpdateProductHandler implements RequestHandler<Map<String, Object>, Response> {

    private final ProductService productService;

    public UpdateProductHandler() {
        this.productService = new ProductService();
    }

    @Override
    public Response handleRequest(Map<String, Object> input, Context context) {
        try {
//            String id = (String) input.get("pathParameters").get("id");

            // Cast the input object to the expected type
            APIGatewayProxyRequestEvent requestEvent = (APIGatewayProxyRequestEvent) input.get("requestEvent");

            // Access path parameters from the request event
            Map<String, String> pathParameters = requestEvent.getPathParameters();
            String id = pathParameters.get("id");

            // Get the values from the request body
            Map<String, Object> requestBody = (Map<String, Object>) input.get("body");
            String updatedName = (String) requestBody.get("name");
            Double updatedPrice = (Double) requestBody.get("price");

            ProductRequest updateRequest = new ProductRequest();
            updateRequest.setName(updatedName);
            updateRequest.setPrice(updatedPrice);

            productService.updateProduct(id, updateRequest);

            return new Response("Product updated successfully", 200);
        } catch (Exception e) {
            return new Response("Error updating product: " + e.getMessage(), 500);
        }
    }
}
