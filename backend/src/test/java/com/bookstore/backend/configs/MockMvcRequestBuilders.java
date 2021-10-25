package com.bookstore.backend.configs;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public abstract class MockMvcRequestBuilders{
    
    public MockMvcRequestBuilders() {
    }

    public static MockHttpServletRequestBuilder get(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.GET, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder get(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.GET, uri);
    }

    public static MockHttpServletRequestBuilder post(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.POST, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder post(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.POST, uri);
    }

    public static MockHttpServletRequestBuilder put(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.PUT, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder put(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.PUT, uri);
    }

    public static MockHttpServletRequestBuilder patch(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.PATCH, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder patch(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.PATCH, uri);
    }

    public static MockHttpServletRequestBuilder delete(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.DELETE, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder delete(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.DELETE, uri);
    }

    public static MockHttpServletRequestBuilder options(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.OPTIONS, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder options(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.OPTIONS, uri);
    }

    public static MockHttpServletRequestBuilder head(String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(HttpMethod.HEAD, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder head(URI uri) {
        return new MockHttpServletRequestBuilder(HttpMethod.HEAD, uri);
    }

    public static MockHttpServletRequestBuilder request(HttpMethod method, String urlTemplate, Object... uriVars) {
        return new MockHttpServletRequestBuilder(method, urlTemplate, uriVars);
    }

    public static MockHttpServletRequestBuilder request(HttpMethod httpMethod, URI uri) {
        return new MockHttpServletRequestBuilder(httpMethod, uri);
    }

    public static MockHttpServletRequestBuilder request(String httpMethod, URI uri) {
        return new MockHttpServletRequestBuilder(httpMethod, uri);
    }

}