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

import java.util.Arrays;
import java.util.List;

/**
 * Created by rayt on 6/18/17.
 */
public class IstioHeaderPropagatoinRequestFilter extends HeaderPropagationRequestFilter {
    private static List<String> HEADERS = Arrays.asList(
            "x-request-id",
            "x-b3-traceid",
            "x-b3-spanid",
            "x-b3-parentspanid",
            "x-b3-sampled",
            "x-b3-flags",
            "x-ot-span-context",
            "user-agent"
    );

    public IstioHeaderPropagatoinRequestFilter() {
        super(HEADERS);
    }
}
