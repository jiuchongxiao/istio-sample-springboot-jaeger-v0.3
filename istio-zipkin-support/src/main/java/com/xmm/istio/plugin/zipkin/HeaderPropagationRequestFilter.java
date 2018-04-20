// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.xmm.istio.plugin.zipkin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by rayt on 6/18/17.
 */
public class HeaderPropagationRequestFilter extends OncePerRequestFilter {
    private static final Log log = LogFactory.getLog(
            MethodHandles.lookup().lookupClass());
    private final List<String> headersToPropagate;

    public HeaderPropagationRequestFilter(List<String> headersToPropagate) {
        this.headersToPropagate = headersToPropagate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        for (String header : headersToPropagate) {
            String value = httpServletRequest.getHeader(header);
            HeaderPropagationHolder.put(header, value);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
