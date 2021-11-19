package api.rest.domain;

public class Constants {

public static final String SAMPLE_INPUT = "{\"players\":[{\"id\":\"123\",\"account\":{\"accountId\":\"12345\",\"balance\":10.0},\"name\":\"Sam\"},{\"id\":\"456\",\"account\":{\"accountId\":\"6789\",\"balance\":20.0},\"name\":\"Raj\"}]}";
public static final String ERROR_MSG = "{\"Status\" : \"ERROR\",\"code\":\"ERR_123\",\"message\":\"The initialization data not valid, please provide the data in the format \" " +
        SAMPLE_INPUT + "}";
public static final String SUCCESS_MSG = "{\"Status\": \"Success\", \"Code\":\"200_OK\"}";
}
