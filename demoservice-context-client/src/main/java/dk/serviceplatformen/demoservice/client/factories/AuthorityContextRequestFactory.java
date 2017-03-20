package dk.serviceplatformen.demoservice.client.factories;

import dk.serviceplatformen.xml.schemas.authoritycontext._1.AuthorityContextType;
import dk.serviceplatformen.xml.schemas.callcontext._1.CallContextType;
import dk.serviceplatformen.xml.wsdl.soap11.sp.demo._1.CallDemoServiceRequestType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:authoritycontext.properties")
public class AuthorityContextRequestFactory {

    @Value("${municipality.cvr}")
    private String municipalityCvr;

    @Value("${callers.service.call.identifier}")
    private String callersServiceCallIdentifier;

    @Value("${on.behalf.of.user}")
    private String onBehalfOfUser;

    public CallDemoServiceRequestType getDemoServiceRequestType(String requestMessage) {
        CallDemoServiceRequestType demoServiceRequestType = new CallDemoServiceRequestType();
        AuthorityContextType authorityContextType = getAuthorityContext();
        CallContextType callContextType = getCallContext();
        demoServiceRequestType.setAuthorityContext(authorityContextType);
        demoServiceRequestType.setCallContext(callContextType);
        demoServiceRequestType.setMessageString(requestMessage);
        return demoServiceRequestType;
    }

    private AuthorityContextType getAuthorityContext() {
        AuthorityContextType authorityContextType = new AuthorityContextType();
        authorityContextType.setMunicipalityCVR(municipalityCvr);
        return authorityContextType;
    }

    private CallContextType getCallContext() {
        CallContextType callContextType = new CallContextType();
        callContextType.setOnBehalfOfUser(onBehalfOfUser);
        callContextType.setCallersServiceCallIdentifier(callersServiceCallIdentifier);
        return callContextType;
    }
}
