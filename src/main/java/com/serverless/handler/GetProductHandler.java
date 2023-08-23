package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.serverless.model.Product;
import com.serverless.response.Response;
import com.serverless.service.ProductService;

import java.util.Map;

public class GetProductHandler implements RequestHandler<Map<String, Object>, Response> {

    private final ProductService productService;

    public GetProductHandler() {
        this.productService = new ProductService();
    }

    @Override
    public Response handleRequest(Map<String, Object> input, Context context) {
        try {
            // Cast the input object to the expected type
            APIGatewayProxyRequestEvent requestEvent = (APIGatewayProxyRequestEvent) input.get("requestEvent");

            // Access path parameters from the request event
            Map<String, String> pathParameters = requestEvent.getPathParameters();
            String id = pathParameters.get("id");

            // Get the product using the ProductService
            Product product = productService.getProductById(id);

            if (product != null) {
                return new Response(product, 200);
            } else {
                return new Response("Product not found", 404);
            }
        } catch (Exception e) {
            return new Response("Error retrieving product: " + e.getMessage(), 500);
        }
    }
}
