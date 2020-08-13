package org.ad.request.handler;

import java.io.IOException;

public interface RequestHandler {

    String getResponse(String requestStr) throws IOException;

}
