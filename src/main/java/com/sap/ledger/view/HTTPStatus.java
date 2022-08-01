package com.sap.ledger.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HTTPStatus {
	OK(200, "OK"),
	CREATED(201, "Created"),
	ACCEPTED(202, "Accepted"),
	NO_CONTENT(204, "No Content"),
	MULTI_STATUS(207, "Multi-Status"),
	MOVED_PERMANENTLY(301, "Moved Permanently"),
	FOUND(302, "Found"),
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),
	PERMANENT_REDIRECT(308, "Permanent Redirect"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	NOT_ACCEPTABLE(406, "Not Acceptable"),
	PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
	REQUEST_TIMEOUT(408, "Request Timeout"),
	PRECONDITION_FAILED(412, "Precondition Failed"),
	PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
	URI_TOO_LONG(414, "URI Too Long"),
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
	TOO_MANY_REQUESTS(429, "Too Many Requests"),
	REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
	UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	NOT_IMPLEMENTED(501, "Not Implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	GATEWAY_TIMEOUT(504, "Gateway Timeout"),
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
	BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
	NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),
	
	TOKEN_EXPIRED(521, "Token Expired"),
	MAIL_UNAVAILABLE(522, "Mail Unavailable");

	@Getter
    private Integer code;

	@Getter
    private String name;
	
	
    private HTTPStatus(Integer code){
        this.code = code;
    }
    
    public static HTTPStatus getHTTPStatusEnum(Integer code) {
		for (HTTPStatus e : values()) {
			if (e.getCode().equals(code))
				return e;
		}
		return null;
	}
    
    public static Integer getHTTPStatuType(Integer code) {
		for (HTTPStatus e : values()) {
			if (e.getCode().equals(code))
				return e.getCode();
		}
		return null;
	}

}
