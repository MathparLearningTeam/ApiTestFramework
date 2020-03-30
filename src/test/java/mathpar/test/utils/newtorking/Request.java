package mathpar.test.utils.newtorking;

import mathpar.test.contexts.AuthenticationContext;
import mathpar.test.contexts.ProfileContext;
import mathpar.test.contexts.RequestContext;
import mathpar.test.utils.newtorking.results.FailedRequestResult;
import mathpar.test.utils.newtorking.results.SuccessfulJsonResult;
import mathpar.test.utils.newtorking.results.SuccessfulResult;
import mathpar.test.utils.properties.Properties;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

public class Request{
    private static final RestTemplate restTemplate = new RestTemplate();

    private final String url;
    private final HttpMethod method;
    private boolean authorizationRequired;
    private final HttpHeaders headers;
    private Object body;
    private List<String> urlParams = new LinkedList<>();

    private Long profileId;

    public Request(String url, HttpMethod method){
        this.url = url;
        this.method = method;
        this.authorizationRequired = true;
        this.headers = new HttpHeaders();
    }

    public Request withBody(Object body){
        this.body = body;
        return this;
    }

    public Request withUrlParams(String... param){
        this.urlParams = List.of(param);
        return this;
    }

    public Request withProfile(long profileId){
        this.profileId = profileId;
        return this;
    }

    public Request removeAuthorization(){
        this.authorizationRequired = false;
        return this;
    }

    public void send(){
        createHeaders();
        try {
            var response = restTemplate.exchange(url, method, new HttpEntity<>(body, headers), byte[].class, urlParams.toArray());
            RequestContext.addToHistory(url, mapToSuccess(response));
        }catch (RestClientResponseException e){
            RequestContext.addToHistory(url, mapToFailure(e));
        }
    }

    private void createHeaders(){
        if (authorizationRequired){
            var token = AuthenticationContext.getAuthenticationToken();
            headers.add(Properties.AUTHENTICATION_TOKEN_NAME, token);
            if(profileId!=null) headers.add(Properties.CHOSEN_PROFILE_ID, String.valueOf(profileId));
        }
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
    }

    public static SuccessfulResult mapToSuccess(ResponseEntity<byte[]> response){
        var contentType = response.getHeaders().getContentType();
        var body = response.getBody();

        if (contentType == null || body == null)
            return new SuccessfulResult();
        if (MediaType.APPLICATION_JSON.equals(contentType))
            return new SuccessfulJsonResult(new JSONObject(new String(body)));

        throw new RuntimeException(String.format("No converter for content-type %s was found", contentType.toString()));
    }

    public static FailedRequestResult mapToFailure(RestClientResponseException exception){
        return new FailedRequestResult(exception.getRawStatusCode(), exception.getStatusText(), exception.getResponseBodyAsString());
    }

    public static void sendRequestWithoutBody(String url, HttpMethod method, String... urlParams){
        new Request(url, method).withUrlParams(urlParams).send();

    }

    public static void sendRequestWithBody(String url, HttpMethod method, Object body, String... urlParams){
        new Request(url, method).withBody(body).withUrlParams(urlParams).send();
    }

    public static void sendRequestWithoutBodyWithoutAuthentication(String url, HttpMethod method, String... urlParams){
        new Request(url, method).withUrlParams(urlParams).removeAuthorization().send();
    }

    public static void sendRequestWithBodyWithoutAuthentication(String url, HttpMethod method, Object body, String... urlParams){
        new Request(url, method).withBody(body).withUrlParams(urlParams).removeAuthorization().send();
    }

    public static void sendSchoolRequestWithBody(String url, HttpMethod method, Object body, long profileId, String... urlParams){
        new Request(url, method).withBody(body).withUrlParams(urlParams).withProfile(profileId).send();
    }

    public static void sendSchoolRequestWithoutBody(String url, HttpMethod method, long profileId, String... urlParams){
        new Request(url, method).withUrlParams(urlParams).withProfile(profileId).send();
    }


    public static void sendSchoolRequestWithBody(String url, HttpMethod method, Object body, String... urlParams){
        new Request(url, method).withBody(body).withUrlParams(urlParams).withProfile(ProfileContext.getCurrentProfile().getProfileId()).send();
    }

    public static void sendSchoolRequestWithoutBody(String url, HttpMethod method, String... urlParams){
        new Request(url, method).withUrlParams(urlParams).withProfile(ProfileContext.getCurrentProfile().getProfileId()).send();
    }
}
