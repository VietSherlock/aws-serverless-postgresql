package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.request.ProductRequest;
import com.serverless.response.Response;
import com.serverless.service.ProductService;

public class CreateProductHandler implements RequestHandler<ProductRequest, Response> {

    private final ProductService productService;

    public CreateProductHandler() {
        this.productService = new ProductService();
    }

    @Override
    public Response handleRequest(ProductRequest productRequest, Context context) {
        try {
            productService.createProduct(productRequest);
            return new Response("Product created successfully", 201);
        } catch (Exception e) {
            return new Response("Error creating product: " + e.getMessage(), 500);
        }
    }
}
