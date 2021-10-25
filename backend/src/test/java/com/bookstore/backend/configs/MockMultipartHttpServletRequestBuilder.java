package com.bookstore.backend.configs;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

public class MockMultipartHttpServletRequestBuilder extends MockHttpServletRequestBuilder {
    private final List<MockMultipartFile> files = new ArrayList<>();
    private final MultiValueMap<String, Part> parts = new LinkedMultiValueMap<>();

    public MockMultipartHttpServletRequestBuilder(String urlTemplate, Object... uriVariables) {
        super(HttpMethod.POST, urlTemplate, uriVariables);
        super.contentType(MediaType.MULTIPART_FORM_DATA);
    }

    public MockMultipartHttpServletRequestBuilder(URI uri) {
        super(HttpMethod.POST, uri);
        super.contentType(MediaType.MULTIPART_FORM_DATA);
    }

    public MockMultipartHttpServletRequestBuilder file(String name, byte[] content) {
        this.files.add(new MockMultipartFile(name, content));
        return this;
    }

    public MockMultipartHttpServletRequestBuilder file(MockMultipartFile file) {
        this.files.add(file);
        return this;
    }

    public MockMultipartHttpServletRequestBuilder part(Part... parts) {
        Assert.notEmpty(parts, "'parts' must not be empty");
        Part[] var2 = parts;
        int var3 = parts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Part part = var2[var4];
            this.parts.add(part.getName(), part);
        }

        return this;
    }

    public Object merge(@Nullable Object parent) {
        if (parent == null) {
            return this;
        } else if (parent instanceof MockHttpServletRequestBuilder) {
            super.merge(parent);
            if (parent instanceof MockMultipartHttpServletRequestBuilder) {
                MockMultipartHttpServletRequestBuilder parentBuilder = (MockMultipartHttpServletRequestBuilder)parent;
                this.files.addAll(parentBuilder.files);
                parentBuilder.parts.keySet().stream().forEach((name) -> {
                    List var10000 = (List)this.parts.putIfAbsent(name, parentBuilder.parts.get(name));
                });
            }

            return this;
        } else {
            throw new IllegalArgumentException("Cannot merge with [" + parent.getClass().getName() + "]");
        }
    }

    protected final MockHttpServletRequest createServletRequest(ServletContext servletContext) {
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest(servletContext);
        this.files.stream().forEach(request::addFile);
        this.parts.values().stream().flatMap(Collection::stream).forEach(request::addPart);
        if (!this.parts.isEmpty()) {
            (new StandardMultipartHttpServletRequest(request)).getMultiFileMap().values().stream().flatMap(Collection::stream).forEach(request::addFile);
        }

        return request;
    }

    public static MockMultipartHttpServletRequestBuilder multipart(String urlTemplate, Object... uriVars) {
        return new MockMultipartHttpServletRequestBuilder(urlTemplate, uriVars);
    }

    public static MockMultipartHttpServletRequestBuilder multipart(URI uri) {
        return new MockMultipartHttpServletRequestBuilder(uri);
    }
}
