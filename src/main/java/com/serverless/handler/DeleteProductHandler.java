package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.serverless.response.Response;
import com.serverless.service.ProductService;

import java.util.Map;

public class DeleteProductHandler implements RequestHandler<Map<String, Object>, Response> {

    private final ProductService productService;

    public DeleteProductHandler() {
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

            // Delete the product using the ProductService
            productService.deleteProduct(id);

            return new Response("Product deleted successfully", 200);
        } catch (Exception e) {
            return new Response("Error deleting product: " + e.getMessage(), 500);
        }
    }
}
